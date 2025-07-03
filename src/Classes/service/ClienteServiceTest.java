package src.Classes.service;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Classes.service.ClienteService;
import src.Classes.model.Cliente;
import src.Classes.exception.ClienteNaoEncontradoException;

import java.sql.SQLException;
import java.util.List;

public class ClienteServiceTest {

    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();

        try {
            // TESTE 1 – Cadastrar cliente com cachorro
            System.out.println("=== TESTE 1: Cadastro de cliente com cachorro ===");
            clienteService.cadastrarClienteComCachorro(
                "Carlos Henrique",
                "carlos@email.com",
                "111.222.333-44",
                "Thor",
                "Pastor Alemão",
                4,
                30.0
            );
            System.out.println("✔ Cliente cadastrado com sucesso!");

            // TESTE 2 – Listar todos os clientes
            System.out.println("\n=== TESTE 2: Listar todos os clientes ===");
            List<Cliente> clientes = clienteService.listarTodosClientes();
            for (Cliente c : clientes) {
                System.out.println("Cliente: " + c.getNomeCompleto() + ", CPF: " + c.getCPF());
            }

            if (!clientes.isEmpty()) {
                Cliente cliente = clientes.get(0);
                int clienteId = cliente.getId();

                // TESTE 3 – Buscar cliente por ID
                System.out.println("\n=== TESTE 3: Buscar cliente por ID ===");
                Cliente buscado = clienteService.buscarClientePorId(clienteId);
                if (buscado != null) {
                    System.out.println("✔ Cliente encontrado: " + buscado.getNomeCompleto());
                } else {
                    System.out.println("✘ Cliente não encontrado.");
                }

                // TESTE 4 – Atualizar cliente
                System.out.println("\n=== TESTE 4: Atualizar nome do cliente ===");
                buscado.setNomeCompleto("Carlos Atualizado");
                clienteService.atualizarCliente(buscado);
                System.out.println("✔ Nome atualizado para: " + buscado.getNomeCompleto());

                // TESTE 5 – Adicionar outro cachorro ao cliente existente
                System.out.println("\n=== TESTE 5: Adicionar novo cachorro ao cliente ===");
                clienteService.adicionarCachorroAClienteExistente(
                    clienteId,
                    "Max",
                    "Poodle",
                    2,
                    9.5
                );
                System.out.println("✔ Novo cachorro adicionado!");

                // TESTE 6 – Remover cliente
                System.out.println("\n=== TESTE 6: Remover cliente ===");
                clienteService.removerCliente(clienteId);
                System.out.println("✔ Cliente removido com sucesso!");
            } else {
                System.out.println("✘ Nenhum cliente encontrado para executar os testes seguintes.");
            }

        } catch (SQLException e) {
            System.out.println("⚠ Erro de SQL: " + e.getMessage());
        } catch (ClienteNaoEncontradoException e) {
            System.out.println("⚠ Erro de cliente: " + e.getMessage());
        }
    }
}
