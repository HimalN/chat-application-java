package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.sql.SQLOutput;

public class ChatFormController {
    @FXML
    private JFXButton emoji;
    @FXML
    private ImageView btnMini;
    @FXML
    private Label lblUser;
    @FXML
    private TextField txtChatField;
    @FXML
    private VBox vBox;

    Socket socket;
    DataInputStream reader;
    DataOutputStream writer;
    String condition = "";

    public void initialize(){
        /*lblUser.setText(LoginFormController.username);
        new Thread(()->{
            try {
                Socket socket = new Socket("localhost",3002);
                reader = new DataInputStream(socket.getInputStream());
                writer = new DataOutputStream(socket.getOutputStream());

                while (true){
                    String type = reader.readUTF();

                    if (type.equals("TEXT")){
                        String message = reader.readUTF();
                        System.out.println(message);
                        Platform.runLater(()->{
                            if (condition.equals("this")){
                                HBox hBox = new HBox();
                                hBox.setStyle("-fx-alignment: top-right;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
                                Label messageLbl = new Label("Me: " + message);
                                messageLbl.setStyle("-fx-background-color:  #1B1464;-fx-background-radius:15;-fx-font-size: 14;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
                                hBox.getChildren().add(messageLbl);
                                vBox.getChildren().add(hBox);
                                condition = "";
                            }else {
                                HBox hBox = new HBox();
                                hBox.setStyle("-fx-alignment: top-left;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
                                Label messageLbl = new Label(LoginFormController.username+": "+message);
                                messageLbl.setStyle("-fx-background-color:  #4B6EAF;-fx-background-radius:15;-fx-font-size: 14;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
                                hBox.getChildren().add(messageLbl);
                                vBox.getChildren().add(hBox);
                            }
                        });
                    }else if (type.equals("IMAGE")){
                        String message = reader.readUTF();
                        System.out.println(message);
                        int file = reader.readInt();
                        byte [] fileData = new byte[file];
                        reader.readFully(fileData);

                        Platform.runLater(() ->{
                            javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView();
                            imageView.setPreserveRatio(true);
                            imageView.setFitWidth(150);
                            imageView.setFitHeight(200);

                            try{
                                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData);
                                Image image = new Image(byteArrayInputStream);
                                imageView.setImage(image);

                                if (condition.equals("this")){
                                    Label label = new Label("Me:");
                                    label.setStyle("-fx-background-color:  #27ae60;-fx-background-radius:15;-fx-font-size: 14;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
                                    BorderPane borderPane1 = new BorderPane();
                                    borderPane1.setRight(label);

                                    BorderPane borderPane = new BorderPane();
                                    borderPane.setRight(imageView);
                                    vBox.getChildren().add(borderPane1);
                                    vBox.getChildren().add(borderPane);
                                    condition = "";
                                }else {
                                    Label label = new Label(message);
                                    label.setStyle("-fx-background-color:  #9b2b2b;-fx-background-radius:15;-fx-font-size: 14;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center-right;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
                                    BorderPane borderPane1 = new BorderPane();
                                    borderPane1.setLeft(label);

                                    BorderPane borderPane = new BorderPane();
                                    borderPane.setLeft(imageView);
                                    vBox.getChildren().add(borderPane1);
                                    vBox.getChildren().add(borderPane);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        });
                    }
                }

            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }).start();*/
        lblUser.setText(LoginFormController.username);
        new Thread(()->{
            try {
                Socket socket = new Socket("localhost", 3002);
                reader = new DataInputStream(socket.getInputStream());
                writer = new DataOutputStream(socket.getOutputStream());

                while (true){
                    String type = reader.readUTF();

                    if(type.equals("TEXT")){
                        String message = reader.readUTF();
                        System.out.println(message);
                        Platform.runLater(()->{
                            if(condition.equals("this")){
                                HBox hBox = new HBox();
                                hBox.setStyle("-fx-alignment: top-right;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
                                Label messageLbl = new Label("Me: "+message);
                                messageLbl.setStyle("-fx-background-color:  #1B1464;-fx-background-radius:15;-fx-font-size: 14;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
                                hBox.getChildren().add(messageLbl);
                                vBox.getChildren().add(hBox);
                                condition = "";
                            } else {
                                HBox hBox = new HBox();
                                hBox.setStyle("-fx-alignment: top-left;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
                                Label messageLbl = new Label(LoginFormController.username+": "+message);
                                messageLbl.setStyle("-fx-background-color:  #4B6EAF;-fx-background-radius:15;-fx-font-size: 14;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
                                hBox.getChildren().add(messageLbl);
                                vBox.getChildren().add(hBox);
                            }
                        });
                    } else if (type.equals("IMAGE")) {
                        String message = reader.readUTF();
                        System.out.println(message);
                        int file = reader.readInt();
                        byte [] fileData = new byte[file];
                        reader.readFully(fileData);

                        Platform.runLater(() -> {
                            javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView();
                            imageView.setPreserveRatio(true);
                            imageView.setFitWidth(150);
                            imageView.setFitHeight(200);

                            try {
                                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData);
                                Image image = new Image(byteArrayInputStream);
                                imageView.setImage(image);

                                if (condition.equals("this")) {
                                    Label label = new Label("Me:");
                                    label.setStyle("-fx-background-color:  #27ae60;-fx-background-radius:15;-fx-font-size: 14;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
                                    BorderPane borderPane1 = new BorderPane();
                                    borderPane1.setRight(label);

                                    BorderPane borderPane = new BorderPane();
                                    borderPane.setRight(imageView);
                                    vBox.getChildren().add(borderPane1);
                                    vBox.getChildren().add(borderPane);
                                    condition = "";
                                } else {
                                    Label label = new Label(message);
                                    label.setStyle("-fx-background-color:  #9b2b2b;-fx-background-radius:15;-fx-font-size: 14;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center-right;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
                                    BorderPane borderPane1 = new BorderPane();
                                    borderPane1.setLeft(label);

                                    BorderPane borderPane = new BorderPane();
                                    borderPane.setLeft(imageView);
                                    vBox.getChildren().add(borderPane1);
                                    vBox.getChildren().add(borderPane);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }).start();
    }

    @FXML
    void btnAttachOnAction(ActionEvent event) {
        String sender = lblUser.getText();
        condition = "this";
        Platform.runLater(() -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                    new FileChooser.ExtensionFilter("JPEG Files", "*.jpg"));
            File selectedFiles = fileChooser.showOpenDialog(null);

            if (selectedFiles != null) {
                try {
                    byte [] fileData = Files.readAllBytes(selectedFiles.toPath());

                    writer.writeUTF("IMAGE");
                    writer.writeUTF(sender);
                    writer.writeInt(fileData.length);
                    writer.write(fileData);
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void btnEmojiOnAction(ActionEvent event) {

    }
    @FXML
    void btnMinimizeOnAction(ActionEvent event) {

    }
    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {
        condition = "this";
        String message = txtChatField.getText().trim();
        String sender = lblUser.getText();
        writer.writeUTF("TEXT");
        writer.writeUTF(message);
        writer.flush();
        System.out.println(sender+": "+message);
        txtChatField.clear();

    }
}
