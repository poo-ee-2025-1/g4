package src.Controller;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import src.Classes.model.Cachorro;
import src.Classes.model.Cliente;
import src.Classes.model.ClienteDog;
import src.Classes.model.OrdemServico;
import src.Classes.model.enums.StatusOS;
import src.Classes.service.ClienteService;
import src.Classes.service.OrdemServicoService;
import src.Classes.view.OrdemServicoView;
import src.Main;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GestaoOS {

    //<editor-fold desc="FXML Fields">
    @FXML private TableView<OrdemServicoView> tabelaOS;
    @FXML private TableColumn<OrdemServicoView, Integer> colunaOsId;
    @FXML private TableColumn<OrdemServicoView, String> colunaOsCliente;
    @FXML private TableColumn<OrdemServicoView, String> colunaOsCachorro;
    @FXML private TableColumn<OrdemServicoView, StatusOS> colunaOsStatus;

    @FXML private TextField idOsField; // Campo invisível ou desabilitado para guardar o ID
    @FXML private ComboBox<Cliente> clienteComboBox;
    @FXML private ComboBox<Cachorro> cachorroComboBox;
    @FXML private TextArea descricaoArea;
    @FXML private TextField valorField;
    @FXML private DatePicker dataPicker;
    @FXML private ComboBox<StatusOS> statusComboBox;

    @FXML private Button novoButton;
    @FXML private Button salvarButton;
    @FXML private Button finalizarButton;
    //</editor-fold>

    private final OrdemServicoService osService;
    private final ClienteService clienteService; // Precisamos dele para listar os clientes

    public GestaoOS() {
        this.osService = new OrdemServicoService();
        this.clienteService = new ClienteService();
    }

    @FXML
    public void initialize() {
        configurarTabela();
        configurarComboBoxes();
        configurarListeners();

        carregarTabelaOS();
        carregarClientes();
    }

    private void configurarTabela() {
        colunaOsId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colunaOsStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        // Para objetos aninhados (ex: pegar o nome do cliente de uma OS),
        // usamos um Callback para extrair a propriedade.
        colunaOsCliente.setCellValueFactory(cellData ->
                cellData.getValue().clienteNomeProperty()
        );
        colunaOsCachorro.setCellValueFactory(cellData ->
                cellData.getValue().cachorroNomeProperty()
        );
    }

    private void configurarComboBoxes() {
        // Popula o ComboBox de status com todos os valores do Enum
        statusComboBox.getItems().setAll(StatusOS.values());

        // Diz ao ComboBox de Cliente para mostrar o nome do cliente, e não o objeto.
        clienteComboBox.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente cliente) {
                return cliente == null ? "" : cliente.getNomeCompleto();
            }
            @Override
            public Cliente fromString(String string) { return null; }
        });
        cachorroComboBox.setConverter(new StringConverter<Cachorro>() {
            @Override
            public String toString(Cachorro cachorro) {
                return cachorro == null ? "" : cachorro.getNome();
            }
            @Override
            public Cachorro fromString(String string) { return null; }
        });
    }

    private void configurarListeners() {
        // Listener 1: Atualiza o ComboBox de cachorros quando um cliente é selecionado
        clienteComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                // Pega a coleção de cachorros do cliente e popula o ComboBox de cachorros
                List<Cachorro> listaDeCachorros = newVal.getDogs().stream().map(ClienteDog::getCachorro).collect(Collectors.toList());
                cachorroComboBox.setItems(FXCollections.observableArrayList(listaDeCachorros));
            }else{
                cachorroComboBox.getItems().clear();
            }
        });

        // Listener 2: Preenche o formulário quando uma OS é selecionada na tabela
        tabelaOS.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                OrdemServicoView osViewSelecionada = newVal;

                OrdemServico osOriginal = osViewSelecionada.getOrdemServicoModel();

                populateForm(osOriginal);

                salvarButton.setDisable(false);
                finalizarButton.setDisable(false);
            }else{
                limparFormulario();
                salvarButton.setDisable(true);
                finalizarButton.setDisable(true);
            }
        });
    }

    // --- Métodos de Ação dos Botões ---

    @FXML
    void handleNovaOSAction(ActionEvent event) {
        limparFormulario();
    }
    @FXML
    void handleRetornaAdm(ActionEvent event) throws IOException {
        Main.changeScene("AfterLoginAdm.fxml");
    }
    @FXML
    void handleSalvarAction(ActionEvent event) {
        try {
            // Pega os dados do formulário
            Cliente cliente = clienteComboBox.getValue();
            Cachorro cachorro = cachorroComboBox.getValue();
            String descricao = descricaoArea.getText();
            double valor = Double.parseDouble(valorField.getText());

            // Lógica para decidir se é uma criação ou atualização
            if (idOsField.getText().isEmpty()) {
                // CRIAR NOVA OS
                osService.criarNovaOS(cliente, cachorro, descricao, valor);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Nova Ordem de Serviço criada!");
            } else {
                // ATUALIZAR OS EXISTENTE
                OrdemServico os = osService.buscarPorId(Integer.parseInt(idOsField.getText()));
                os.setCliente(cliente);
                os.setCachorro(cachorro);
                os.setDescricao(descricao);
                os.setValor(valor);
                os.setStatus(statusComboBox.getValue());
                //... outros campos se necessário
                osService.atualizarOrdem(os);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Ordem de Serviço atualizada!");
            }

            carregarTabelaOS();
            limparFormulario();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao Salvar", e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    void handleFinalizarAction(ActionEvent event) {
        OrdemServicoView osViewSelecionada = tabelaOS.getSelectionModel().getSelectedItem();
        if (osViewSelecionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Nenhuma Seleção", "Por favor, selecione uma OS para finalizar.");
            return;
        }
        try {
            OrdemServico osParaFinalizar = osViewSelecionada.getOrdemServicoModel();
            osService.finalizarOrdem(osParaFinalizar);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "OS #" + osParaFinalizar.getId() + " finalizada!");
            carregarTabelaOS();
            limparFormulario();
        } catch (IllegalStateException | SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao Finalizar", e.getMessage());
            e.printStackTrace();
        }
    }


    private void carregarTabelaOS() {
        try {
            List<OrdemServico> listaDeModelos = osService.listarTodas();
            List<OrdemServicoView> listaParaView = listaDeModelos.stream().map(OrdemServicoView::new).collect(Collectors.toList());

            tabelaOS.setItems(FXCollections.observableArrayList(listaParaView));
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Banco", "Não foi possível carregar as Ordens de Serviço.");
            e.printStackTrace();
        }
    }

    private void carregarClientes() {
        try {
            List<Cliente> listaClientes = clienteService.listarTodosClientes();
            clienteComboBox.setItems(FXCollections.observableArrayList(listaClientes));
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Banco", "Não foi possível carregar a lista de clientes.");
        }
    }

    private void populateForm(OrdemServico os) {
        idOsField.setText(String.valueOf(os.getId()));
        clienteComboBox.setValue(os.getCliente());
        // A seleção do cliente vai disparar o listener que popula os cachorros,
        cachorroComboBox.setValue(os.getCachorro());
        descricaoArea.setText(os.getDescricao());
        valorField.setText(String.format("%.2f", os.getValor()));
        statusComboBox.setValue(os.getStatus());
        if (os.getDataAbertura() != null) {
            dataPicker.setValue(os.getDataAbertura().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
    }

    private void limparFormulario() {
        idOsField.clear();
        tabelaOS.getSelectionModel().clearSelection();
        clienteComboBox.getSelectionModel().clearSelection();
        cachorroComboBox.getItems().clear();
        cachorroComboBox.getSelectionModel().clearSelection();
        descricaoArea.clear();
        valorField.clear();
        statusComboBox.getSelectionModel().clearSelection();
        dataPicker.setValue(null);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String conteudo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }
}