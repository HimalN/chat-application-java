package lk.ijse.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static lk.ijse.controller.LoginFormController.username;

public class Server {
    public static ArrayList<DataOutputStream> clientList = new ArrayList<>();
    public static void start() throws IOException {
        new Thread(()->{
            try {
                ServerSocket serverSocket = new ServerSocket(3002);
                Socket socket;
                while (true) {
                    System.out.println("[SERVER] : Waiting for Clients...");
                    //socket is referred to as a client.
                    socket = serverSocket.accept();
                    System.out.println("[SERVER] : Client " + username + " Accepted");
                    TimeUnit.SECONDS.sleep(1);
                    ClientHandler clients = new ClientHandler(socket, clientList);
                    new Thread(clients).start();
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}