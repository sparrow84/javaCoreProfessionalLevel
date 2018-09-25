package com.company.lesson02.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;
    private AuthService authService;

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            clients = new Vector<>();
            authService = new BaseAuthService();

            System.out.println("Server started... Waiting clients...");

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("Client connected " + socket.getInetAddress() + " " + socket.getPort() + " " + socket.getLocalPort());
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        broadcastClientList();
    }

    public void unsubscribe(ClientHandler clientHandler){
        clients.remove(clientHandler);
        broadcastClientList();
    }

    public boolean isNickBusy(String nick) {
        for(ClientHandler clientHandler: clients){
            if(clientHandler.getNick().equals(nick)){
                return true;
            }
        }
        return false;
    }

    public void broadcastMsg(String msg) {
        for(ClientHandler clientHandler: clients){
            clientHandler.sendMsg(msg);
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void sendPrivateMsg (ClientHandler from, String nickTo, String msg){
        for(ClientHandler clientHandler: clients) {
            if (clientHandler.getNick().equals(nickTo)) {
                clientHandler.sendMsg("from " + from.getNick() + ": " + msg);
                from.sendMsg("to client " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMsg("Client with nickname " + nickTo + " not found");
    }

    public void broadcastClientList() {
        StringBuilder sb = new StringBuilder("/clientslist ");
        for (ClientHandler clientHandler: clients){
            sb.append(clientHandler.getNick() + " ");
        }
        String out = sb.toString();
        for (ClientHandler clientHandler: clients){
            clientHandler.sendMsg(out);
        }
    }
}
