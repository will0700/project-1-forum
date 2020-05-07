package dev.williamchung.utils;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionUtil {
    protected static String url;
    protected static String username;
    protected static String password;
    protected static String defaultSchema;

    public abstract Connection getConnection() throws SQLException;

    public String getDefaultSchema() {
        return defaultSchema;
    }
}
