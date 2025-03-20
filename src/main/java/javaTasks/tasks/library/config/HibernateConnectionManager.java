package javaTasks.tasks.library.config;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateConnectionManager {

    private SessionFactory sessionFactory;

    public HibernateConnectionManager() {
        this.sessionFactory = SessionFactoryProvider.getSessionFactory();
    }

    public Session openSession() {
        try {
            return sessionFactory.openSession();
        } catch (HibernateException e) {
            throw new HibernateException("Error opening session", e);
        }
    }

    public Transaction beginTransaction(Session session) {
        try {
            return session.beginTransaction();
        } catch (HibernateException e) {
            throw new HibernateException("Error beginning transaction", e);
        }
    }

    public void commitTransaction(Transaction transaction) {
        try {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        } catch (HibernateException e) {
            throw new HibernateException("Error committing transaction", e);
        }
    }

    public void closeSession(Session session) {
        try {
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (HibernateException e) {
            throw new HibernateException("Error closing session", e);
        }
    }
}
