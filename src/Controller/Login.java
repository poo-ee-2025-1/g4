package src.Controller;
import src.Main;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Login {
    public Login(){

    }
    @FXML
    private Button entrar;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException{
        Main m = new Main();
        if(username.getText().toString().equals("Cliente") && password.getText().toString().equals("1234")){
            wrongLogin.setText("Sucesso");

            m.changeScene("AfterLoginClient.fxml");
        }
        if(username.getText().toString().equals("Cliente") && password.getText().toString().equals("1234")){
            wrongLogin.setText("Sucesso");

            m.changeScene("AfterLoginClient.fxml");
        }
    }



}