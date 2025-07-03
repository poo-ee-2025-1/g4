package src.Classes.model;


import org.junit.Test;
import static org.junit.Assert.*;
import src.Classes.model.Database;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.SQLException;

public class DatabaseTest {

    @Test
    public void testGetConnectionSuccess() throws SQLException {
        Database db = new Database("testdb.sqlite");

        JdbcConnectionSource conn1 = db.getConnection();
        assertNotNull("A conexão não deve ser nula", conn1);

        JdbcConnectionSource conn2 = db.getConnection();
        // A conexão deve ser a mesma instância (cacheada)
        assertSame("Deve retornar a mesma conexão na segunda chamada", conn1, conn2);

        db.close();
    }

    @Test
    public void testCloseConnection() throws SQLException {
        Database db = new Database("testdb.sqlite");
        JdbcConnectionSource conn = db.getConnection();
        db.close();

        // Após fechar, o campo connection deve ser null
        // Não dá para acessar diretamente, então vamos chamar getConnection de novo e esperar nova conexão
        JdbcConnectionSource newConn = db.getConnection();
        assertNotSame("Após fechar, deve retornar uma nova conexão", conn, newConn);

        db.close();
    }

    @Test(expected = SQLException.class)
    public void testGetConnectionWithNullDatabaseName() throws SQLException {
        Database db = new Database(null);
        db.getConnection();  // Deve lançar SQLException
    }
}
