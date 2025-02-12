package javaTasks.tasks.library.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private final HikariDataSource ds;

    public DatabaseConnectionManager(PropertyConfig propertyConfig) {
        HikariConfig config = new HikariConfig();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Error loading postgres driver", ex);
        }
        config.setJdbcUrl(String.format("jdbc:postgresql://%s:5432/%s", propertyConfig.getHost(), propertyConfig.getDbName()));
        config.setUsername(propertyConfig.getUsername());
        config.setPassword(propertyConfig.getPassword());

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
