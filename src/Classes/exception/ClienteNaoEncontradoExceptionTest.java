package src.Classes.exception;



import org.junit.Test;
import static org.junit.Assert.*;
import src.Classes.exception.ClienteNaoEncontradoException;

public class ClienteNaoEncontradoExceptionTest {

    @Test
    public void testExceptionMessage() {
        String mensagem = "Cliente não encontrado com ID 123";
        ClienteNaoEncontradoException ex = new ClienteNaoEncontradoException(mensagem);

        assertEquals(mensagem, ex.getMessage());
    }

    @Test(expected = ClienteNaoEncontradoException.class)
    public void testThrowException() throws ClienteNaoEncontradoException {
        throw new ClienteNaoEncontradoException("Erro ao buscar cliente");
    }
}
