package src.Classes.service;


import org.junit.Test;
import static org.junit.Assert.*;
import src.Classes.service.ClienteService;
import src.Classes.model.Cliente;
import src.Classes.exception.ClienteNaoEncontradoException;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import src.Classes.service.ClienteService;
import src.Classes.model.Cliente;
import src.Classes.exception.ClienteNaoEncontradoException;

import java.sql.SQLException;
import java.util.List;

public class ClienteServiceTest {

    @Test
    public void testCadastrarBuscarAtualizarAdicionarRemover() throws SQLException, ClienteNaoEncontradoException {
        ClienteService clienteService = new ClienteService();

        // Cadastrar cliente com cachorro
        clienteService.cadastrarClienteComCachorro(
            "Carlos Henrique",
            "carlos@email.com",
            "111.222.333-44",
            "Thor",
            "Pastor Alemão",
            4,
            30.0
        );

        // Buscar o cliente recém cadastrado pelo CPF (único)
        List<Cliente> clientes = clienteService.listarTodosClientes();
        Cliente clienteCadastrado = null;
        for (Cliente c : clientes) {
            if ("111.222.333-44".equals(c.getCPF())) {
                clienteCadastrado = c;
                break;
            }
        }
        assertNotNull("Cliente cadastrado deve ser encontrado pelo CPF", clienteCadastrado);

        int clienteId = clienteCadastrado.getId();

        // Buscar cliente pelo id
        Cliente buscado = clienteService.buscarClientePorId(clienteId);
        assertNotNull("Cliente deve ser encontrado pelo ID", buscado);
        assertEquals("Carlos Henrique", buscado.getNomeCompleto());

        // Atualizar nome do cliente
        buscado.setNomeCompleto("Carlos Atualizado");
        clienteService.atualizarCliente(buscado);

        Cliente atualizado = clienteService.buscarClientePorId(clienteId);
        assertEquals("Carlos Atualizado", atualizado.getNomeCompleto());

        // Adicionar cachorro ao cliente
        clienteService.adicionarCachorroAClienteExistente(clienteId, "Max", "Poodle", 2, 9.5);

        // Remover cliente
        clienteService.removerCliente(clienteId);
        Cliente deletado = clienteService.buscarClientePorId(clienteId);
        assertNull("Cliente deve ter sido removido", deletado);
    }
}
