package src.Classes.model;


import org.junit.Test;
import static org.junit.Assert.*;
import src.Classes.model.DatabaseManager;
import src.Classes.model.Cliente;
import src.Classes.model.Cachorro;
import src.Classes.model.ClienteDog;
import src.Classes.model.OrdemServico;

import com.j256.ormlite.dao.Dao;

public class DatabaseManagerTest {

    @Test
    public void testSingletonInstance() {
        DatabaseManager instance1 = DatabaseManager.getInstance();
        DatabaseManager instance2 = DatabaseManager.getInstance();
        assertNotNull("Instância não deve ser nula", instance1);
        assertSame("As duas instâncias devem ser a mesma (singleton)", instance1, instance2);
    }

    @Test
    public void testDaosInitialization() {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        Dao<Cliente, Integer> clienteDao = dbManager.getClienteDao();
        Dao<Cachorro, Integer> cachorroDao = dbManager.getCachorroDao();
        Dao<ClienteDog, Integer> clienteDogDao = dbManager.getClienteDogDao();
        Dao<OrdemServico, Integer> ordemServicoDao = dbManager.getOrdemServicoDao();

        assertNotNull("ClienteDao deve ser inicializado", clienteDao);
        assertNotNull("CachorroDao deve ser inicializado", cachorroDao);
        assertNotNull("ClienteDogDao deve ser inicializado", clienteDogDao);
        assertNotNull("OrdemServicoDao deve ser inicializado", ordemServicoDao);
    }

    @Test
    public void testCloseConnection() {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        try {
            dbManager.close();
        } catch (Exception e) {
            fail("Fechamento da conexão deve ocorrer sem exceção, mas ocorreu: " + e.getMessage());
        }
    }
}
