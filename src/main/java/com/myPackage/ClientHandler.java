package com.myPackage;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientHandler(Socket socket) throws Exception {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendMessage(String msg) {
        writer.println(msg);
    }

    @Override
    public void run() {
        try {
            writer.println("Welcome to the chat!");
            String msg;
            while ((msg = reader.readLine()) != null) {
                System.out.println("Received: " + msg);
                Database.saveMessage(msg); // Log to DB
                for (ClientHandler client : ChatServer.clients) {
                    if (client != this) {
                        client.sendMessage(msg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
