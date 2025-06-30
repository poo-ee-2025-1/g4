package src.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import src.Classes.exception.ClienteNaoEncontradoException;
import src.Classes.service.ClienteService;
import src.Main;
import src.Classes.model.Cliente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CadastroCliente {
    @FXML
    private Button returnButton;
    @FXML
    private TextField idCliente;
    @FXML
    private TextField nomeCliente;
    @FXML
    private TextField numeroCliente;
    @FXML
    private TextField cpfCliente;
    @FXML
    private TextField idCachorro;
    @FXML
    private TextField nomeCachorro;
    @FXML
    private TextField racaCachorro;
    @FXML
    private TextField pesoCachorro;
    @FXML
    private TextField idadeCachorro;
    @FXML
    private CheckBox somenteCachorro;
    @FXML
    private Button adicionarButton;
    @FXML
    private Button atualizarButton;
    @FXML
    private Button deletarButton;
    @FXML
    private Button cancelarButton;
    @FXML
    private Button buscarButton;
    @FXML
    private Button salvarButton;
    @FXML
    private TableView <Cliente> nomes;
    @FXML
    private TableColumn<Cliente, Integer> colunaId;
    @FXML
    private TableColumn<Cliente, String> colunaNome;
    @FXML
    private TableColumn<Cliente, String> colunaContato;

    private final ClienteService clienteService;

    public CadastroCliente() {
        this.clienteService = new ClienteService();
    }

    @FXML
    public void initialize() {
        atualizarButton.setDisable(true);
        deletarButton.setDisable(true);
        somenteCachorro.selectedProperty().addListener((obs, oldVal, newVal) -> {
            boolean desabilitarCamposCliente = newVal;
            nomeCliente.setDisable(desabilitarCamposCliente);
            numeroCliente.setDisable(desabilitarCamposCliente);
            cpfCliente.setDisable(desabilitarCamposCliente);
            idCliente.setPromptText(desabilitarCamposCliente ? "Id do Dono(Obrigatório)" : "ID do Cliente(Busca)");
        });
        //Aqui diz para a coluna da table view buscar a table id no banco de dados, assim sucessivamente com os outros dados
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaContato.setCellValueFactory(new PropertyValueFactory<>("numeroContato"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nomeCompleto"));
        nomes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    // Este código dentro das chaves {} é executado SEMPRE que uma nova linha é selecionada (ou desmarcada).
                    // 'newValue' é o objeto Cliente da linha recém-selecionada.
                    // Se o usuário clicar fora ou a seleção for limpa, 'newValue' será null.
                    if (newValue != null) {
                        // Temos um cliente selecionado!
                        populateForm(newValue);

                        atualizarButton.setDisable(false);
                        deletarButton.setDisable(false);
                    }
                }
        );
        carregarDadosDaTabela();
    }

    @FXML
    void handleSalvarAction(ActionEvent event) {
        if (somenteCachorro.isSelected()) {
            adicionarNovoCachorroAClienteExistente();
        } else {
            cadastroNovoClienteECachorro();
        }
        carregarDadosDaTabela();
    }

    @FXML
    void handleDeletarAction(ActionEvent event) {
        try {
            int id = Integer.parseInt(idCliente.getText());
            clienteService.removerCliente(id);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cliente removido com sucesso");
            limparCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Entrada Inválida", "Por favor, insira um ID número para deletar. ");
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro no banco", "Não foi possível remover o cliente.");
            e.printStackTrace();
        } catch(ClienteNaoEncontradoException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Cliente não encontrado", "Não foi possível remover o cliente.");
        }
        carregarDadosDaTabela();
    }

    @FXML
    void handleLimparAction(ActionEvent event) {
        limparCampos();
    }

    @FXML
    void handleRetornaAdm(ActionEvent event) throws IOException {
        Main.changeScene("AfterLoginAdm.fxml");
    }

    @FXML
    void handleBuscarAction(ActionEvent event) {
        try {
            int id = Integer.parseInt(idCliente.getText());
            Cliente cliente = clienteService.buscarClientePorId(id);
            if (cliente != null) {
                populateForm(cliente);
            } else {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Não Encontrado", "Nenhum cliente encontrado com o ID " + id);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Entrada Inválida", "Por favor, insira um ID numérico para buscar.");
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro no Banco", "Ocorreu uma falha ao buscar os dados.");
            e.printStackTrace();
        }
    }
    @FXML
    void handleAtualizarAction(ActionEvent event){
        String idStr = idCliente.getText();

        if(idStr == null || idStr.isEmpty()){
            mostrarAlerta(Alert.AlertType.WARNING, "Nenhum Cliente Selecionado", "Selecione um cliente na tabela primeiro para pdoer atualizar ");
            return;
        }
        try {
            Cliente clienteParaAtualizar = new Cliente();
            clienteParaAtualizar.setId(Integer.parseInt(idStr));

            clienteParaAtualizar.setNomeCompleto(nomeCliente.getText());
            clienteParaAtualizar.setNumeroContato(numeroCliente.getText());
            clienteParaAtualizar.setCPF(cpfCliente.getText());

            clienteService.atualizarCliente(clienteParaAtualizar);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Dados do Cliente atualizado");

            carregarDadosDaTabela();
            limparCampos();

        }catch (NumberFormatException e ){
            mostrarAlerta(Alert.AlertType.ERROR,"Erro de Formato", "O ID deve ser um numero inteiro");
        }catch (SQLException e){
            mostrarAlerta(Alert.AlertType.ERROR,"Erro de banco de dados", "Não foi possivel atualizar o cliente");
            e.printStackTrace();
        }
    }

    private void cadastroNovoClienteECachorro() {
        try {
            if (nomeCliente.getText().isEmpty() || nomeCachorro.getText().isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Vazios", "Nome do cliente e do cachorro são obrigatorios");
                return;
            }
            clienteService.cadastrarClienteComCachorro(
                    nomeCliente.getText(),
                    numeroCliente.getText(),
                    cpfCliente.getText(),
                    nomeCachorro.getText(),
                    racaCachorro.getText(),
                    Integer.parseInt(idadeCachorro.getText()),
                    Double.parseDouble(pesoCachorro.getText())
            );
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Novo cliente e cachorro cadastrados");
            limparCampos();
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Erro de banco", "Falha ao salvar os dados");
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Formato inválido", "ID do cliente e idade devem ser número");
        }
    }

    private void populateForm(Cliente cliente){
        idCliente.setText(Integer.toString(cliente.getId()));
        nomeCliente.setText(cliente.getNomeCompleto());
        numeroCliente.setText(cliente.getNumeroContato());
        cpfCliente.setText(cliente.getCPF());
    }
    private void limparCampos(){
        idCliente.clear();
        nomeCliente.clear();
        numeroCliente.clear();
        cpfCliente.clear();
        idCachorro.clear();
        nomeCachorro.clear();
        racaCachorro.clear();
        pesoCachorro.clear();
        idadeCachorro.clear();
        somenteCachorro.setSelected(false);
        //Tabela Cliente que aparece embaixo do cadastro
        nomes.getSelectionModel().clearSelection();
        atualizarButton.setDisable(true);
        deletarButton.setDisable(true);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String conteudo){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }
    private void adicionarNovoCachorroAClienteExistente() {
        try {
            // Coleta e valida dados da UI
            if (idCliente.getText().isEmpty() || nomeCachorro.getText().isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Vazios", "ID do cliente e nome do cachorro são obrigatórios.");
                return;
            }
            // Chama o serviço
            clienteService.adicionarCachorroAClienteExistente(
                    Integer.parseInt(idCliente.getText()),
                    nomeCachorro.getText(),
                    racaCachorro.getText(),
                    Integer.parseInt(idadeCachorro.getText()),
                    Double.parseDouble(pesoCachorro.getText())
            );
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cachorro adicionado ao cliente!");
            limparCampos();
        } catch (ClienteNaoEncontradoException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Não Encontrado", e.getMessage());
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Banco", "Falha ao salvar os dados.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Formato Inválido", "ID do cliente e idade devem ser números.");
        }
    }

    private void carregarDadosDaTabela(){
        try{
            List<Cliente> clientesDoBanco = clienteService.listarTodosClientes();
            ObservableList<Cliente> clienteObservaveis = FXCollections.observableArrayList(clientesDoBanco);
            nomes.setItems(clienteObservaveis);
        }catch (SQLException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Banco", "Não foi possivel carregar a lista de cliente.");
            e.printStackTrace();
        }
    }



}
