package br.ufjf.dcc192;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConnection() throws SQLException {
        String strJDBC = System.getenv("JDBC_DATABASE_URL");
        return DriverManager.getConnection(strJDBC);
    }
}
