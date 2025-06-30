package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import src.Main;

import java.io.IOException;

public class AfterLoginClient {
    @FXML
    private Button logout;
    //Botão que quando apertado retorna para a tela anterior de login
    public void userLogout(ActionEvent event) throws IOException{

        Main.changeScene("main.fxml");
    }
}