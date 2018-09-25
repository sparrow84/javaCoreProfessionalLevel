package com.company.lesson02.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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


            // Поток отслеживает таймаут для отключения при простое во время логина
            Thread t3 = new Thread(() -> {
                try {
                    sleep(120000);
                    sendMsg("Server: Connection timed out.");
                    System.out.println("ClientHandler.this.socket before -> " + ClientHandler.this.socket);
                    System.out.println("Socket is closed -> " + ClientHandler.this.socket.isClosed());
                    ClientHandler.this.socket.close();
                    //ClientHandler.this.socket = null;
                    System.out.println("Socket is closed -> " + ClientHandler.this.socket.isClosed());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


            Thread t1 = new Thread(() -> {

                System.out.println("---debug--- ClientHandler Thread t1 START");

                try {

                    System.out.println("---debug--- ClientHandler Thread t1 START go try");

                    while (true) {

                        System.out.println("---debug--- ClientHandler Thread t1 START go try go while");

                        if (ClientHandler.this.socket.isClosed()) {
                            System.out.println("Client Handler is interrupted -> " + Thread.currentThread().isInterrupted());
                            Thread.currentThread().interrupt();
                            System.out.println("Client Handler is interrupted -> " + Thread.currentThread().isInterrupted());
                        }

                        System.out.println("---debug--- ClientHandler Thread t1 START 63 String msg = in.readUTF();");

                        String msg = in.readUTF();

                        System.out.println("---debug--- ClientHandler recive msg: " + msg);

                        if (msg.startsWith("/auth")) {
                            String[] data = msg.split("\\s");
                            if (data.length == 3) {
                                String chatNick = server.getAuthService().authByLoginAndPassword(data[1], data[2]);
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
