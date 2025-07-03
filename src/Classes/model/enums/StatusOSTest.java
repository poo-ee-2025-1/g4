package src.Classes.model.enums;



import org.junit.Test;
import static org.junit.Assert.*;
import src.Classes.model.enums.StatusOS;

public class StatusOSTest {

    @Test
    public void testEnumValuesExistem() {
        assertNotNull(StatusOS.valueOf("ABERTA"));
        assertNotNull(StatusOS.valueOf("EM_ANDAMENTO"));
        assertNotNull(StatusOS.valueOf("AGUARDANDO_PAGAMENTO"));
        assertNotNull(StatusOS.valueOf("FINALIZADA"));
        assertNotNull(StatusOS.valueOf("CANCELADA"));
    }

    @Test
    public void testToStringIgualNome() {
        assertEquals("ABERTA", StatusOS.ABERTA.toString());
        assertEquals("EM_ANDAMENTO", StatusOS.EM_ANDAMENTO.toString());
        assertEquals("AGUARDANDO_PAGAMENTO", StatusOS.AGUARDANDO_PAGAMENTO.toString());
    }

    @Test
    public void testQuantidadeStatus() {
        assertEquals(5, StatusOS.values().length);
    }
}
