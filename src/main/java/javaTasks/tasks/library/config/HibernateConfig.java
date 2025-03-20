package javaTasks.tasks.library.config;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateConfig {

    private PropertyConfig propertyConfig;

    public HibernateConfig() {
        propertyConfig = new PropertyConfig();
    }

    private Configuration configure() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.use_sql_comments", "true");

        configuration.setProperty("hibernate.connection.url", String
                .format("jdbc:postgresql://%s:5432/%s", propertyConfig.getHost(), propertyConfig.getDbName()));
        configuration.setProperty("hibernate.default_schema", "libschema");
        configuration.setProperty("hibernate.connection.username", propertyConfig.getUsername());
        configuration.setProperty("hibernate.connection.password", propertyConfig.getPassword());

        configuration.addResource("Author.hbm.xml");
        configuration.addResource("Book.hbm.xml");
        configuration.addResource("Journal.hbm.xml");

        return configuration;
    }

    public SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = configure();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            throw new HibernateException("Error when build SessionFactory", e);
        }
    }
}
