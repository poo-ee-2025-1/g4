package com.g4.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/petshopdb"; // rascunho do "bando de dados"
    private static final String USER = "seu_usuario"; // rascunho do usuario
    private static final String PASS = "sua_senha"; // rascunho da senha

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
