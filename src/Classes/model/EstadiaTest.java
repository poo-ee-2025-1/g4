package src.Classes.model;

import org.junit.Test;
import static org.junit.Assert.*;
import src.Classes.model.Estadia;
import src.Classes.model.Cliente;
import src.Classes.model.Cachorro;

public class EstadiaTest {

    @Test
    public void testGettersSetters() {
        Estadia estadia = new Estadia();

        Cachorro cachorro = new Cachorro("Rex", "Labrador", 5, 20.0);
        Cliente cliente = new Cliente("João Silva", "99999-9999", "123.456.789-00");
        String dataEntrada = "2025-07-01";
        String dataSaida = "2025-07-10";
        String[] servicos = {"Banho", "Tosa"};

        estadia.setCachorro(cachorro);
        estadia.setDono(cliente);
        estadia.setDataEntrada(dataEntrada);
        estadia.setDataSaida(dataSaida);
        estadia.setServicos(servicos);

        assertEquals(cachorro, estadia.getCachorro());
        assertEquals(cliente, estadia.getDono());
        assertEquals(dataEntrada, estadia.getDataEntrada());
        assertEquals(dataSaida, estadia.getDataSaida());
        assertArrayEquals(servicos, estadia.getServicos());
    }
}
