package dev.williamchung.repositories;

import dev.williamchung.utils.ConnectionUtil;
import dev.williamchung.utils.PostgresConnectionUtil;

/**
 * This is the AbstractRepository class.
 * We have created an abstract repository to use static variables across all the Repositories,
 * since they will all connect to the same database.
 * These static variables are pulled out of the System Environment variables set in the project configuration.
 */
public abstract class AbstractRepository {
    protected static String url = System.getenv("RDS_url");
    protected static String username = System.getenv("RDS_username");
    protected static String password = System.getenv("RDS_password");
    protected static String defaultSchema = System.getenv("RDS_defaultSchema");
    protected static ConnectionUtil connectionUtil = new PostgresConnectionUtil(url, username, password, defaultSchema);
}
