package javaTasks.tasks.library.config;

public class PropertyConfig {

    private static final String HOST = "app.db.host";
    private static final String DB_NAME = "app.db.name";
    private static final String USERNAME = "app.db.username";
    private static final String PASSWORD = "app.db.password";

    public String getHost() {
        return getSystemProperty(HOST);
    }

    public String getDbName() {
        return getSystemProperty(DB_NAME);
    }

    public String getUsername() {
        return getSystemProperty(USERNAME);
    }

    public String getPassword() {
        return getSystemProperty(PASSWORD);
    }

    private static String getSystemProperty(String key) {
        String propertyValue = System.getenv(key) == null ? System.getProperty(key) : System.getenv(key);
        if (propertyValue == null) throw new IllegalArgumentException(String
                .format("Please specify '%s' value in env variables.", key));
        return propertyValue;
    }
}
