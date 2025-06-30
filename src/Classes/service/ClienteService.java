package src.Classes.service;
//Coordena o gerenciador do banco de dados, classe responsavel pelo CRUD em si
import com.j256.ormlite.stmt.DeleteBuilder;
import src.Classes.exception.ClienteNaoEncontradoException;
import src.Classes.model.Cachorro;
import src.Classes.model.Cliente;
import src.Classes.model.ClienteDog;
import src.Classes.model.DatabaseManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {

    private final Dao<Cliente, Integer> clienteDao;
    private final Dao<Cachorro, Integer> cachorroDao;
    private final Dao<ClienteDog, Integer> clienteDogDao;

    public ClienteService() {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        this.clienteDao = dbManager.getClienteDao();
        this.cachorroDao = dbManager.getCachorroDao();
        this.clienteDogDao = dbManager.getClienteDogDao();
    }

    public void cadastrarClienteComCachorro(String nomeCliente, String contatoCliente, String CPF, String nomeCachorro, String racaCachorro, int idadeCachorro, double pesoCachorro) throws SQLException {
        Cachorro novoCachorro = new Cachorro(nomeCachorro, racaCachorro, idadeCachorro, pesoCachorro);
        cachorroDao.create(novoCachorro);

        Cliente novoCliente = new Cliente(nomeCliente, contatoCliente, CPF);
        clienteDao.create(novoCliente);

        ClienteDog associacao = new ClienteDog(novoCliente, novoCachorro);
        clienteDogDao.create(associacao);
    }

    public void adicionarCachorroAClienteExistente(int idCliente, String nomeCachorro, String racaCachorro, int idadeCachorro, double pesoCachorro) throws SQLException, ClienteNaoEncontradoException {
        Cliente clienteExistente = clienteDao.queryForId(idCliente);
        if (clienteExistente == null) {
            throw new SQLException("Cliente com ID" + idCliente + "Não encontrado.");

        }
        Cachorro novoCachorro = new Cachorro(nomeCachorro, racaCachorro, idadeCachorro, pesoCachorro);
        cachorroDao.create(novoCachorro);

        ClienteDog novaAssociacao = new ClienteDog(clienteExistente, novoCachorro);
        clienteDogDao.create(novaAssociacao);

    }

    public Cliente buscarClientePorId(int idCliente) throws SQLException {
        return clienteDao.queryForId(idCliente);
    }

    public List<Cliente> listarTodosClientes() throws SQLException {
        return clienteDao.queryForAll();
    }

    public void removerCliente(int idCliente) throws SQLException, ClienteNaoEncontradoException {
        Cliente clienteParaVerificar = clienteDao.queryForId(idCliente);
        if (clienteParaVerificar == null){
            throw new ClienteNaoEncontradoException("Impossivel remover, ID: " + idCliente + "não foi encontrado");
        }
        DeleteBuilder<ClienteDog, Integer> deleteBuilder = clienteDogDao.deleteBuilder();
        deleteBuilder.where().eq("cliente_id", idCliente);
        deleteBuilder.delete();

        clienteDao.deleteById(idCliente);
    }

    public void atualizarCliente(Cliente cliente) throws SQLException{
        clienteDao.update(cliente);
    }


}
