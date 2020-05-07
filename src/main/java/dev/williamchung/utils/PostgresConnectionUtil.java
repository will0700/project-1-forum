package dev.williamchung.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionUtil extends ConnectionUtil {
    //Inject DriverManager from postgresql
    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    //Constructors
    public PostgresConnectionUtil() {
        defaultSchema = "public";
    }
    public PostgresConnectionUtil(String url, String username, String password, String schema) {
        ConnectionUtil.url = url;
        ConnectionUtil.username = username;
        ConnectionUtil.password = password;
        defaultSchema = schema;
    }

    //Override interface method
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
