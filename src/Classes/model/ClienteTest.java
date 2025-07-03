package src.Classes.model;

import org.junit.Test;
import static org.junit.Assert.*;
import src.Classes.model.Cliente;

public class ClienteTest {

    @Test
    public void testConstrutorGettersSetters() {
        Cliente cliente = new Cliente("Ana Paula", "99999-9999", "123.456.789-00");
        assertEquals("Ana Paula", cliente.getNomeCompleto());
        assertEquals("99999-9999", cliente.getNumeroContato());
        assertEquals("123.456.789-00", cliente.getCPF());

        cliente.setNomeCompleto("Carlos Silva");
        cliente.setNumeroContato("88888-8888");
        cliente.setCPF("111.222.333-44");
        cliente.setId(10);

        assertEquals("Carlos Silva", cliente.getNomeCompleto());
        assertEquals("88888-8888", cliente.getNumeroContato());
        assertEquals("111.222.333-44", cliente.getCPF());
        assertEquals(10, cliente.getId());

        // dogs começa null (ORM que preencheria)
        assertNull(cliente.getDogs());
    }
}
