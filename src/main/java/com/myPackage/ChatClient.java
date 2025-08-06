package com.myPackage;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 12345);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        // Receive messages
        new Thread(() -> {
            try {
                String serverMsg;
                while ((serverMsg = reader.readLine()) != null) {
                    System.out.println("Server: " + serverMsg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Send messages
        String msg;
        while ((msg = keyboard.readLine()) != null) {
            writer.println(msg);
        }
    }
}
