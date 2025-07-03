package src.Classes.service;


import org.junit.jupiter.api.Test;
import src.Classes.model.Cachorro;
import src.Classes.model.Cliente;
import src.Classes.model.OrdemServico;
import src.Classes.model.enums.StatusOS;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class OrdemServicoServiceTest {

    @Test
    public void testCriarNovaOS_comDadosValidos_retornaOrdemServicoValida() {
        // Arrange
        OrdemServicoService service = new OrdemServicoService();

        Cliente cliente = new Cliente("Ana Souza", "11988887777", "12345678901");
        Cachorro cachorro = new Cachorro("Bolt", "Golden Retriever", 3, 28.5);
        String descricao = "Consulta + vacinação";
        double valor = 150.0;

        // Act
        OrdemServico os = null;
        try {
            os = service.criarNovaOS(cliente, cachorro, descricao, valor);
        } catch (SQLException e) {
            fail("Exceção inesperada: " + e.getMessage());
        }

        // Assert
        assertNotNull(os, "A ordem de serviço não deve ser nula");
        assertEquals(cliente, os.getCliente(), "Cliente deve ser igual ao fornecido");
        assertEquals(cachorro, os.getCachorro(), "Cachorro deve ser igual ao fornecido");
        assertEquals(descricao, os.getDescricao(), "Descrição incorreta");
        assertEquals(valor, os.getValor(), 0.001, "Valor incorreto");
        assertEquals(StatusOS.ABERTA, os.getStatus(), "Status inicial deve ser ABERTA");
        assertNotNull(os.getDataAbertura(), "Data de abertura não deve ser nula");
    }
}
