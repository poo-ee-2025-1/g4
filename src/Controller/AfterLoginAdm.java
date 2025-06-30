package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import src.Main;

import java.io.IOException;

public class AfterLoginAdm {
    @FXML
    private Button logout;
    @FXML
    private Button services;
    @FXML
    private Button cadastrarClient;


    public void userLogout(ActionEvent event) throws IOException {
        Main.changeScene("main.fxml");
    }

    public void cadastroClient(ActionEvent event) throws  IOException{
        Main.changeScene("CadastroCliente.fxml");
    }

    public void cadastroOS(ActionEvent event) throws IOException{
        Main.changeScene("GestaoOS.fxml");
    }

}
