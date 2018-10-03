package com.company.lesson04.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static java.lang.Thread.sleep;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            ExecutorService executorService = null;

            executorService.execute(qwe());

//            out.writeUTF("ClientHandler --- TEST 24");

            // Поток отслеживает таймаут для отключения при простое во время логина
            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sleep(120000);
                        ClientHandler.this.sendMsg("Server: Connection timed out_.");
                        System.out.println("ClientHandler.this.socket before -> " + ClientHandler.this.socket);
                        System.out.println("Socket is closed -> " + ClientHandler.this.socket.isClosed());
                        ClientHandler.this.socket.close();
                        //ClientHandler.this.socket = null;
                        System.out.println("Socket is closed -> " + ClientHandler.this.socket.isClosed());
                    } catch (InterruptedException e) {
//                    e.printStackTrace();
                        System.out.println(e);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            


            Thread t1 = new Thread(() -> {
                try {
                    while (true) {
                        if (ClientHandler.this.socket.isClosed()) {
                            System.out.println("Client Handler is interrupted -> " + Thread.currentThread().isInterrupted());
                            Thread.currentThread().interrupt();
                            System.out.println("Client Handler is interrupted -> " + Thread.currentThread().isInterrupted());
                        }
                        String msg = in.readUTF();
                        if (msg.startsWith("/auth")) {
                            String[] data = msg.split("\\s");
                            if (data.length == 3) {

// --- debug block CH59 start --------------------------------------------------------------
                                System.out.println("--- debug block 12 start ---");
                                for (int i = 0; i < data.length; i++) {
                                    System.out.println("data[" + i + "] = " + data[i]);
                                }
                                System.out.println("--- debug block 12 end ---");
// --- debug block CH59 end -----------------------------------------------------------------

                                String chatNick = server.getAuthService().authByLoginAndPassword(data[1], data[2]);

                                System.out.println("--- debug --- 59 " + chatNick);

                                if (chatNick != null) {
                                    if (!server.isNickBusy(chatNick)) {
                                        nick = chatNick;
                                        sendMsg("/authok " + chatNick);
                                        server.subscribe(ClientHandler.this);
                                        t3.interrupt();
                                        break;
                                    } else {
                                        sendMsg("The account is already taken");
                                    }
                                } else {
                                    sendMsg("Invalid login/password");
                                }
                            }
                        }
                    }
                    while (true) {
                        String msg = in.readUTF();
                        System.out.println(nick + ": " + msg);
                        if (msg.startsWith("/")) {
                            if (msg.equals("/end")) break;
                            if (msg.startsWith("/w ")) {
                                String[] data = msg.split("\\s", 3);
                                server.sendPrivateMsg(ClientHandler.this, data[1], data[2]);
                            }
                        } else {
                            server.broadcastMsg(nick + ": " + msg);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    nick = null;
                    server.unsubscribe(ClientHandler.this);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });

            t3.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Runnable qwe() {
        return null;
    }


    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getNick() {
        return nick;
    }
}

//
//    private Runnable qwe() {
//        try {
//            sleep(120000);
//            ClientHandler.this.sendMsg("Server: Connection timed out_.");
//            System.out.println("ClientHandler.this.socket before -> " + ClientHandler.this.socket);
//            System.out.println("Socket is closed -> " + ClientHandler.this.socket.isClosed());
//            ClientHandler.this.socket.close();
//            //ClientHandler.this.socket = null;
//            System.out.println("Socket is closed -> " + ClientHandler.this.socket.isClosed());
//        } catch (InterruptedException e) {
//    //                e.printStackTrace();
//            System.out.println(e);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }