package javaTasks.tasks.library.config;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SessionFactoryProvider {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                HibernateConfig config = new HibernateConfig();
                sessionFactory = config.buildSessionFactory();
            } catch (HibernateException e) {
                throw new HibernateException("Error initializing SessionFactory", e);
            }
        }
        return sessionFactory;
    }
}
