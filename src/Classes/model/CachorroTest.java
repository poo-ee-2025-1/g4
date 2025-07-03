package src.Classes.model;


import org.junit.Test;
import static org.junit.Assert.*;
import src.Classes.model.Cachorro;

public class CachorroTest {

    @Test
    public void testConstrutorGettersSettersToString() {
        Cachorro dog = new Cachorro("Rex", "Labrador", 5, 25.0);

        assertEquals("Rex", dog.getNome());
        assertEquals("Labrador", dog.getRaca());
        assertEquals(5, dog.getIdade());
        assertEquals(25.0, dog.getPeso(), 0.001);
        assertEquals("Rex", dog.toString());

        dog.setNome("Bolt");
        dog.setRaca("Poodle");
        dog.setIdade(3);
        dog.setPeso(10.5);
        dog.setId(99);

        assertEquals("Bolt", dog.getNome());
        assertEquals("Poodle", dog.getRaca());
        assertEquals(3, dog.getIdade());
        assertEquals(10.5, dog.getPeso(), 0.001);
        assertEquals(99, dog.getId());
    }
}

