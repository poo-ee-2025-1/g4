package src.Classes.model;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
import src.Classes.model.Cliente;
import org.junit.jupiter.api.Test;



public class ClienteTest {

    @Test
    public static void main(String[] args) {
        System.out.println("=== TESTE 1: Construtor com parâmetros ===");
        Cliente cliente1 = new Cliente("João da Silva", "99999-9999", "123.456.789-00");
        System.out.println("Nome: Esperado = João da Silva | Obtido = " + cliente1.getNomeCompleto());
        System.out.println("Contato: Esperado = 99999-9999 | Obtido = " + cliente1.getNumeroContato());
        System.out.println("CPF: Esperado = 123.456.789-00 | Obtido = " + cliente1.getCPF());

        System.out.println("\n=== TESTE 2: Setters e Getters ===");
        Cliente cliente2 = new Cliente();
        cliente2.setNomeCompleto("Maria Oliveira");
        cliente2.setNumeroContato("88888-8888");
        cliente2.setCPF("987.654.321-00");
        cliente2.setId(42);

        System.out.println("Nome: Esperado = Maria Oliveira | Obtido = " + cliente2.getNomeCompleto());
        System.out.println("Contato: Esperado = 88888-8888 | Obtido = " + cliente2.getNumeroContato());
        System.out.println("CPF: Esperado = 987.654.321-00 | Obtido = " + cliente2.getCPF());
        System.out.println("ID: Esperado = 42 | Obtido = " + cliente2.getId());

        System.out.println("\n=== TESTE 3: Campo 'dogs' inicialmente nulo ===");
        if (cliente2.getDogs() == null) {
            System.out.println("✔ Campo 'dogs' está nulo como esperado (não inicializado ainda pelo ORM).");
        } else {
            System.out.println("✘ Erro: campo 'dogs' deveria estar nulo.");
        }
    }
}
