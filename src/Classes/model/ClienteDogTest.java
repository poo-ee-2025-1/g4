package src.Classes.model;

import org.junit.Test;
import static org.junit.Assert.*;
import src.Classes.model.Cliente;
import src.Classes.model.Cachorro;
import src.Classes.model.ClienteDog;

public class ClienteDogTest {

    @Test
    public void testConstrutorGettersSetters() {
        Cliente cliente = new Cliente("Laura Mendes", "99999-0000", "123.123.123-00");
        Cachorro cachorro = new Cachorro("Toby", "Beagle", 3, 12.0);

        ClienteDog clienteDog = new ClienteDog(cliente, cachorro);

        assertEquals("Laura Mendes", clienteDog.getCliente().getNomeCompleto());
        assertEquals("Toby", clienteDog.getCachorro().getNome());

        Cliente novoCliente = new Cliente("Pedro Souza", "88888-1234", "987.654.321-00");
        clienteDog.setCliente(novoCliente);
        clienteDog.setId(50);

        assertEquals("Pedro Souza", clienteDog.getCliente().getNomeCompleto());
        assertEquals(50, clienteDog.getId());
    }
}
