package src.Classes.view;



import static org.junit.Assert.*;
import org.junit.Test;
import src.Classes.view.Cachorro;

public class CachorroViewTest {

    @Test
    public void testGettersSetters() {
        // Cria objeto com dados iniciais
        Cachorro c = new Cachorro(1, "Rex", "Pastor Alemão", 5, 30.5);

        // Verifica valores iniciais via getters
        assertEquals(1, c.getId());
        assertEquals("Rex", c.getNome());
        assertEquals("Pastor Alemão", c.getRaca());
        assertEquals(5, c.getIdade());
        assertEquals(30.5, c.getPeso(), 0.0001);

        // Modifica valores via setters
        c.setId(2);
        c.setNome("Bob");
        c.setRaca("Labrador");
        c.setIdade(7);
        c.setPeso(25.2);

        // Verifica valores alterados
        assertEquals(2, c.getId());
        assertEquals("Bob", c.getNome());
        assertEquals("Labrador", c.getRaca());
        assertEquals(7, c.getIdade());
        assertEquals(25.2, c.getPeso(), 0.0001);
    }
}
