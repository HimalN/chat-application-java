package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.ijse.controller.Server;

import java.io.IOException;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        // Load the icon image
        Image icon = new Image(getClass().getResourceAsStream("/images/icons8-parrot-96.png"));
        // Set the taskbar icon
        stage.getIcons().add(icon);
        stage.setTitle("Rio Chat");
        stage.show();
        Server.start();
    }
}