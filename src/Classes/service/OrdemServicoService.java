package src.Classes.service;

import com.j256.ormlite.dao.Dao;
import src.Classes.exception.ClienteNaoEncontradoException;
import src.Classes.model.Cachorro;
import src.Classes.model.Cliente;
import src.Classes.model.DatabaseManager;
import src.Classes.model.OrdemServico;
import src.Classes.model.enums.StatusOS;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrdemServicoService {
    private final Dao<OrdemServico, Integer> osDao;
    private final Dao<Cliente, Integer> clienteDao;

    public OrdemServicoService(){
        DatabaseManager dbManager = DatabaseManager.getInstance();
        this.osDao = DatabaseManager.getInstance().getOrdemServicoDao();
        this.clienteDao = dbManager.getClienteDao();

    }

    public OrdemServico criarNovaOS(Cliente cliente, Cachorro cachorro, String descricao, double valor) throws SQLException{
        if (cliente == null || cachorro == null){
            throw new IllegalArgumentException("Cliente e cachorro não podem ser nulos");
        }
        OrdemServico novaOS = new OrdemServico();
        novaOS.setCliente(cliente);
        novaOS.setCachorro(cachorro);
        novaOS.setDescricao(descricao);
        novaOS.setValor(valor);
        novaOS.setDataAbertura(new Date()); // Data atual
        novaOS.setStatus(StatusOS.ABERTA); // Status inicial
        osDao.create(novaOS);
        return novaOS;
    }

    public OrdemServico buscarPorId(int id) throws SQLException {
        return osDao.queryForId(id);
    }

    public List<OrdemServico> listarTodas() throws SQLException{
        return osDao.queryBuilder().orderBy("dataAbertura", false).query();
    }
    public void atualizarStatus(OrdemServico os, StatusOS novoStatus) throws SQLException{
        os.setStatus(novoStatus);
        osDao.update(os);

    }

    public List<OrdemServico> listarPorStatus(StatusOS status) throws SQLException {
        return osDao.queryBuilder().where().eq("status", status).query();
    }

    public List<OrdemServico> listarPorCliente(int idCliente) throws SQLException, ClienteNaoEncontradoException {
        Cliente cliente = clienteDao.queryForId(idCliente);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente com ID " + idCliente + " não existe.");
        }
        return osDao.queryBuilder().where().eq("cliente_id", cliente.getId()).query();
    }
    public void atualizarOrdem(OrdemServico os) throws SQLException {
        osDao.update(os);
    }
    public void finalizarOrdem(OrdemServico os) throws SQLException, IllegalStateException {
        if (os.getStatus() == StatusOS.FINALIZADA || os.getStatus() == StatusOS.CANCELADA) {
            // Regra de negócio: não se pode finalizar uma OS que já está finalizada ou cancelada.
            throw new IllegalStateException("Esta Ordem de Serviço já foi concluída ou cancelada.");
        }
        os.setStatus(StatusOS.FINALIZADA);
        os.setDataFechamento(new Date()); // Data e hora atuais
        osDao.update(os);
    }

    public void cancelarOrdem(OrdemServico os) throws SQLException {
        if (os.getStatus() == StatusOS.FINALIZADA) {
            throw new IllegalStateException("Não é possível cancelar uma Ordem de Serviço que já foi finalizada.");
        }
        os.setStatus(StatusOS.CANCELADA);
        osDao.update(os);
    }
    public void deletarFisicamente(OrdemServico os) throws SQLException {
        osDao.delete(os);
    }


}
