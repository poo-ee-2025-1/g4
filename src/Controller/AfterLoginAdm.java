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
    private Button cadastro;
    @FXML
    private Button services;
    @FXML
    private Button cadastrarClient;
    @FXML
    private Button agendarH;
    @FXML
    private Button relatorioP;


    public void userLogout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("main.fxml");
    }
}
