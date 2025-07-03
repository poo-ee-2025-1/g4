package src.Classes.model;


import org.junit.Test;
import static org.junit.Assert.*;

import src.Classes.model.OrdemServico;
import src.Classes.model.Cliente;
import src.Classes.model.Cachorro;
import src.Classes.model.enums.StatusOS;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdemServicoTest {

    @Test
    public void testGettersSetters() throws Exception {
        OrdemServico os = new OrdemServico();

        // Objetos simulados
        Cliente cliente = new Cliente("Maria Silva", "99999-9999", "123.456.789-00");
        Cachorro cachorro = new Cachorro("Bob", "Labrador", 5, 25.0);

        // Datas formatadas conforme padrão da classe
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dataAbertura = sdf.parse("01-07-2025 10:30:00");
        Date dataFechamento = sdf.parse("03-07-2025 16:00:00");

        // Definindo valores
        os.setId(100);
        os.setCliente(cliente);
        os.setCachorro(cachorro);
        os.setDataAbertura(dataAbertura);
        os.setDataFechamento(dataFechamento);
        os.setDescricao("Hospedagem com banho incluso");
        os.setStatus(StatusOS.ABERTA); // <- valor correto com base na sua enum
        os.setValor(200.00);

        // Verificações
        assertEquals(100, os.getId());
        assertEquals(cliente, os.getCliente());
        assertEquals(cachorro, os.getCachorro());
        assertEquals(dataAbertura, os.getDataAbertura());
        assertEquals(dataFechamento, os.getDataFechamento());
        assertEquals("Hospedagem com banho incluso", os.getDescricao());
        assertEquals(StatusOS.ABERTA, os.getStatus());
        assertEquals(200.00, os.getValor(), 0.0001);
    }
}
