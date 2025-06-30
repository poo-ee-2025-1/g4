// PACOTE ATUALIZADO: com.g4.controller
package com.g4.controller;

// IMPORTS ATUALIZADOS
import com.g4.model.Cachorro;
import com.g4.database.DatabaseConnection; 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CachorroController {

    @FXML
    private TableView<Cachorro> cachorroTable;
    @FXML
    private TableColumn<Cachorro, Integer> idColumn;
    @FXML
    private TableColumn<Cachorro, String> nomeColumn;
    @FXML
    private TableColumn<Cachorro, String> racaColumn;
    @FXML
    private TableColumn<Cachorro, String> donoColumn;

    @FXML
    private TextField nomeField;
    @FXML
    private TextField racaField;
    @FXML
    private TextField donoField;

    private ObservableList<Cachorro> cachorroData = FXCollections.observableArrayList();

    /**
     * Inicializa o controller. Este método é chamado automaticamente após o FXML ter sido carregado.
     */
    @FXML
    private void initialize() {
        // Configura as colunas da TableView para mapear as propriedades do Model
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        racaColumn.setCellValueFactory(new PropertyValueFactory<>("raca"));
        donoColumn.setCellValueFactory(new PropertyValueFactory<>("dono"));

        // Adiciona os dados à TableView
        cachorroTable.setItems(cachorroData);

        // Carrega os dados do banco de dados ao iniciar
        loadCachorrosFromDatabase();
    }

    /**
     * Método para listar os registros do banco de dados na TableView.
     */
    private void loadCachorrosFromDatabase() {
        cachorroData.clear(); // Limpa a lista antes de carregar
        String sql = "SELECT id, nome, raca, dono FROM cachorros"; // A tabela deve ser 'cachorros'

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cachorro cachorro = new Cachorro();
                cachorro.setId(rs.getInt("id"));
                cachorro.setNome(rs.getString("nome"));
                cachorro.setRaca(rs.getString("raca"));
                cachorro.setDono(rs.getString("dono"));
                cachorroData.add(cachorro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro de Banco de Dados", "Ocorreu um erro ao carregar os dados dos cachorros do banco de dados.", AlertType.ERROR);
        }
    }

    /**
     * Método para criar um novo registro no banco de dados.
     * Chamado quando o botão "Salvar" é clicado.
     */
    @FXML
    private void handleSalvarCachorro() {
        // Obter os dados do formulário
        String nome = nomeField.getText();
        String raca = racaField.getText();
        String dono = donoField.getText();

        // Validar os dados
        if (nome.isEmpty() || raca.isEmpty() || dono.isEmpty()) {
            showAlert("Campos Vazios", "Por favor, preencha todos os campos para cadastrar um novo cachorro.", AlertType.WARNING);
            return;
        }

        // Inserir no banco de dados
        String sql = "INSERT INTO cachorros (nome, raca, dono) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, raca);
            pstmt.setString(3, dono);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Sucesso", "Cachorro cadastrado com sucesso!", AlertType.INFORMATION);
                clearFields();
                loadCachorrosFromDatabase(); // Atualiza a TableView com o novo registro
            } else {
                showAlert("Erro", "Não foi possível cadastrar o cachorro. Tente novamente.", AlertType.ERROR);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro de Banco de Dados", "Ocorreu um erro ao inserir o registro no banco de dados.", AlertType.ERROR);
        }
    }

    /**
     * Limpa os campos do formulário.
     */
    private void clearFields() {
        nomeField.clear();
        racaField.clear();
        donoField.clear();
    }

    /**
     * Exibe um alerta para o usuário.
     */
    private void showAlert(String title, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
