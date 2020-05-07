package dev.williamchung.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The PostgressConnectionUtil is an extension of the ConnectonUtil abstract.
 * This class contains a static method to register the driver.
 * The constructor will pull the database url and credentials from the project configuration's System Environment variables.
 * It also stores a schema name as an instance variable.
 */
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
