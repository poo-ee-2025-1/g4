package src.Classes.model;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Classes.model.Cachorro;

public class CachorroTest {
    
    @Test

    public static void main(String[] args) {

        System.out.println("=== TESTE 1: Criar objeto Cachorro ===");
        Cachorro dog = new Cachorro("Rex", "Labrador", 5, 25.0);
        System.out.println("Nome esperado: Rex -> Obtido: " + dog.getNome());
        System.out.println("Raça esperada: Labrador -> Obtido: " + dog.getRaca());
        System.out.println("Idade esperada: 5 -> Obtido: " + dog.getIdade());
        System.out.println("Peso esperado: 25.0 -> Obtido: " + dog.getPeso());
        System.out.println("toString() esperado: Rex -> Obtido: " + dog.toString());

        System.out.println("\n=== TESTE 2: Usar setters ===");
        dog.setNome("Bolt");
        dog.setRaca("Poodle");
        dog.setIdade(3);
        dog.setPeso(10.5);

        System.out.println("Nome atualizado: Bolt -> Obtido: " + dog.getNome());
        System.out.println("Raça atualizada: Poodle -> Obtido: " + dog.getRaca());
        System.out.println("Idade atualizada: 3 -> Obtido: " + dog.getIdade());
        System.out.println("Peso atualizado: 10.5 -> Obtido: " + dog.getPeso());

        System.out.println("\n=== TESTE 3: Simular ID manualmente (ORM cuida disso normalmente) ===");
        dog.setId(99); // simulando que o banco deu ID 99
        System.out.println("ID esperado: 99 -> Obtido: " + dog.getId());
    }
}
