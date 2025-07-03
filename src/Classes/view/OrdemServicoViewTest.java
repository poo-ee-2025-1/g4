package src.Classes.view;


import static org.junit.Assert.*;
import org.junit.Test;
import src.Classes.view.OrdemServicoView;
import src.Classes.model.OrdemServico;
import src.Classes.model.Cliente;
import src.Classes.model.Cachorro;
import src.Classes.model.enums.StatusOS;

public class OrdemServicoViewTest {

    @Test
    public void testPropriedadesView() {
        // Criar objetos modelo
        Cliente cliente = new Cliente("João Silva", "9999-9999", "123.456.789-00");
        Cachorro cachorro = new Cachorro("Rex", "Pastor Alemão", 5, 30.0);
        OrdemServico os = new OrdemServico();
        
        // Ajustar o modelo
        os.setId(10);
        os.setCliente(cliente);
        os.setCachorro(cachorro);
        os.setStatus(StatusOS.ABERTA);

        // Criar a view
        OrdemServicoView view = new OrdemServicoView(os);

        // Verificar valores das propriedades JavaFX
        assertEquals(10, view.idProperty().get());
        assertEquals("João Silva", view.clienteNomeProperty().get());
        assertEquals("Rex", view.cachorroNomeProperty().get());
        assertEquals(StatusOS.ABERTA, view.statusProperty().get());

        // Verificar acesso ao modelo original
        assertEquals(os, view.getOrdemServicoModel());
    }
}
