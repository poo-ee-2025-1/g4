package com.g4.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/petshopdb"; // <<-- AJUSTE PARA O SEU BANCO DE DADOS
    private static final String USER = "seu_usuario"; // <<-- AJUSTE SEU USUÁRIO
    private static final String PASS = "sua_senha"; // <<-- AJUSTE SUA SENHA

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
