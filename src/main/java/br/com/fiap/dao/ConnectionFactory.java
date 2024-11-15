package br.com.fiap.dao;

import java.sql.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/PIECS";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "FiapyU";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar o driver do banco de dados: " + DRIVER, e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

