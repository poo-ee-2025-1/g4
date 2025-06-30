package src.Classes.exception;

public class ClienteNaoEncontradoException extends Exception {

    // Construtor que aceita uma mensagem de erro.
    public ClienteNaoEncontradoException(String message) {
        // Passa a mensagem para o construtor da classe pai (Exception).
        super(message);
    }
}