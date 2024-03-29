package br.com.desafio_03.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private ConnectionFactory(){}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost/desafio_03", "postgres", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
