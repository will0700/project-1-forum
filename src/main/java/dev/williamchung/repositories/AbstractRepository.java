package dev.williamchung.repositories;

import dev.williamchung.utils.ConnectionUtil;
import dev.williamchung.utils.PostgresConnectionUtil;

public abstract class AbstractRepository {
    protected static String url = System.getenv("RDS_url");
    protected static String username = System.getenv("RDS_username");
    protected static String password = System.getenv("RDS_password");
    protected static String defaultSchema = System.getenv("RDS_defaultSchema");
    protected static ConnectionUtil connectionUtil = new PostgresConnectionUtil(url, username, password, defaultSchema);
}
