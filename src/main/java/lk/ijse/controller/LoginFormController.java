package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUserName;

    public String username;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        username=txtUserName.getText();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/clientForm.fxml"))));
        stage.setTitle(username +"'s chat");
        stage.show();
        txtUserName.clear();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        btnLogin.requestFocus();
    }
}
