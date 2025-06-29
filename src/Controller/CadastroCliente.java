package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import src.Main;

import java.io.IOException;

public class CadastroCliente {
    @FXML
    private Button returnButton;

    public void retornaAdm(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("AfterLoginAdm.fxml");
    }

}
