package lk.ijse.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private Socket socket;
    private static ArrayList<DataOutputStream> clientList;
    private DataInputStream reader;
    private DataOutputStream writer;

    public ClientHandler(Socket socket,ArrayList<DataOutputStream> clientList) throws IOException {
        this.socket = socket;
        this.clientList = clientList;
        this.reader = new DataInputStream(socket.getInputStream());
        this.writer = new DataOutputStream(socket.getOutputStream());
        clientList.add(writer);
    }

    @Override
    public void run() {
        try {
            while (true){
                String type = reader.readUTF();
                if(type.equals("TEXT")){
                    String message = reader.readUTF();
                    broadcastMessage(message);
                } else if (type.equals("IMAGE")) {
                    String img = reader.readUTF();
                    int fileSize = reader.readInt();
                    byte[] fileData = new byte[fileSize];
                    reader.readFully(fileData);
                    broadcastImage(fileData, img);
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                reader.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void broadcastMessage(String message) throws IOException {
        for (DataOutputStream writer : clientList){
            writer.writeUTF("TEXT");
            writer.writeUTF(message);
            writer.flush();
        }
    }

    private void broadcastImage(byte[] fileData, String img){
        for (DataOutputStream writer : clientList) {
            try {
                writer.writeUTF("IMAGE");
                writer.writeUTF(img);
                writer.writeInt(fileData.length);
                writer.write(fileData);
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
