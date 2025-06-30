package src.Classes.view;

import javafx.beans.property.*;
import src.Classes.model.OrdemServico;
import src.Classes.model.enums.StatusOS;

/**
 * ViewModel que adapta um objeto OrdemServico para ser usado em uma TableView do JavaFX.
 * Contém as propriedades observáveis que a UI precisa.
 */
public class OrdemServicoView {


    private final IntegerProperty id;
    private final StringProperty clienteNome; 
    private final StringProperty cachorroNome;
    private final ObjectProperty<StatusOS> status;


    private final OrdemServico ordemServicoModel;


    public OrdemServicoView(OrdemServico os) {
        this.ordemServicoModel = os;

        // Inicializa as propriedades do JavaFX com os dados do modelo
        this.id = new SimpleIntegerProperty(os.getId());
        this.status = new SimpleObjectProperty<>(os.getStatus());

        // Exemplo de como "achatar" dados de objetos aninhados
        this.clienteNome = new SimpleStringProperty(os.getCliente().getNomeCompleto());
        this.cachorroNome = new SimpleStringProperty(os.getCachorro().getNome());
    }



    public IntegerProperty idProperty() { return id; }
    public StringProperty clienteNomeProperty() { return clienteNome; }
    public StringProperty cachorroNomeProperty() { return cachorroNome; }
    public ObjectProperty<StatusOS> statusProperty() { return status; }


    public OrdemServico getOrdemServicoModel() {
        return ordemServicoModel;
    }
}
