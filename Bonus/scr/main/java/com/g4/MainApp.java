package com.g4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Carrega o arquivo FXML a partir do novo caminho dentro da pasta Bonus
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Bonus/src/main/resources/com/seuprojeto/view/CachorroView.fxml"));
            VBox rootLayout = loader.load();

            // Cria a cena
            Scene scene = new Scene(rootLayout);

            // Configura o stage principal
            primaryStage.setScene(scene);
            primaryStage.setTitle("Cadastro de Cachorros - Pet Shop");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
