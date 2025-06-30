package src.Classes.view;

import javafx.beans.property.*;
import src.Classes.model.OrdemServico;
import src.Classes.model.enums.StatusOS;

/**
 * ViewModel que adapta um objeto OrdemServico para ser usado em uma TableView do JavaFX.
 * Contém as propriedades observáveis que a UI precisa.
 */
public class OrdemServicoView {

    // Propriedades do JavaFX para cada campo que queremos exibir
    private final IntegerProperty id;
    private final StringProperty clienteNome; // "Achatamos" o dado. Em vez de um objeto Cliente, temos só o nome.
    private final StringProperty cachorroNome;
    private final ObjectProperty<StatusOS> status;

    // Guarda uma referência ao modelo original, se precisarmos dele
    private final OrdemServico ordemServicoModel;

    /**
     * Construtor que faz a "tradução" do modelo para o ViewModel.
     * @param os O objeto de modelo original vindo do serviço.
     */
    public OrdemServicoView(OrdemServico os) {
        this.ordemServicoModel = os;

        // Inicializa as propriedades do JavaFX com os dados do modelo
        this.id = new SimpleIntegerProperty(os.getId());
        this.status = new SimpleObjectProperty<>(os.getStatus());

        // Exemplo de como "achatar" dados de objetos aninhados
        this.clienteNome = new SimpleStringProperty(os.getCliente().getNomeCompleto());
        this.cachorroNome = new SimpleStringProperty(os.getCachorro().getNome());
    }

    // Métodos de propriedade (os famosos "xxxProperty()") que a TableView vai usar

    public IntegerProperty idProperty() { return id; }
    public StringProperty clienteNomeProperty() { return clienteNome; }
    public StringProperty cachorroNomeProperty() { return cachorroNome; }
    public ObjectProperty<StatusOS> statusProperty() { return status; }

    // Podemos também expor o modelo original, se necessário
    public OrdemServico getOrdemServicoModel() {
        return ordemServicoModel;
    }
}
