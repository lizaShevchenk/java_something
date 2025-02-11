package javaTasks.tasks.library.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private final HikariDataSource ds;

    public DatabaseConnectionManager(String host, String databaseName, String username, String pwd) {
        HikariConfig config = new HikariConfig();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Error loading postgres driver", ex);
        }
        config.setJdbcUrl(String.format("jdbc:postgresql://%s:5432/%s", host, databaseName));
        config.setUsername(username);
        config.setPassword(pwd);

        ds = new HikariDataSource(config);
    }

    public Connection getConnection() {
        try {
            return this.ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
