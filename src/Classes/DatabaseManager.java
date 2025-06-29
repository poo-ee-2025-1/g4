package src.Classes;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:banco_de_dados.db";
    private static DatabaseManager instance;

    private DatabaseManager(){
        try{
            setup();
        } catch (SQLException | IOException e){
            throw new RuntimeException("Falha ao iniciazliar o DatabaseManager", e);
        }
    }

    public static synchronized DatabaseManager getInstance(){
        if (instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    private ConnectionSource connectionSource;

    private Dao<Cliente, Integer> clienteDao;
    private Dao<Cachorro, Integer> cachorroDao;
    private  Dao<ClienteDog, Integer> clienteDogDao;

    private  void setup() throws  SQLException, IOException{
        connectionSource = new JdbcConnectionSource(DATABASE_URL);

        TableUtils.createTableIfNotExists(connectionSource, Cliente.class);
        TableUtils.createTableIfNotExists(connectionSource, Cachorro.class);
        TableUtils.createTableIfNotExists(connectionSource, ClienteDog.class);

        clienteDao = DaoManager.createDao(connectionSource, Cliente.class);
        cachorroDao = DaoManager.createDao(connectionSource, Cachorro.class);
        clienteDogDao = DaoManager.createDao(connectionSource, ClienteDog.class);
    }

    public Dao<Cliente, Integer> getClienteDao(){return clienteDao;}
    public Dao<Cachorro, Integer> getCachorroDao(){return cachorroDao;}
    public Dao<ClienteDog, Integer> getClienteDogDao(){return clienteDogDao;}

    public void close() throws Exception {
        if (connectionSource != null){
            connectionSource.close();
        }
    }



}
