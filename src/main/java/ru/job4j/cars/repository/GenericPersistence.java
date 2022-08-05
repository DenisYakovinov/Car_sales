package ru.job4j.cars.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.cars.exception.RepositoryException;

import javax.persistence.RollbackException;
import java.util.function.Function;

public abstract class GenericPersistence {
    public final SessionFactory sessionFactory;

    GenericPersistence(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected <T> T genericPersist(Function<Session, T> command, String repositoryExceptionMessage) {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T result = command.apply(session);
            transaction.commit();
            return result;
        } catch (HibernateException e) {
            try {
                session.getTransaction().rollback();
            } catch (RollbackException re) {
                throw new RepositoryException(re.getMessage());
            }
            throw new RepositoryException(String.format("%s (%s)", repositoryExceptionMessage, e.getMessage()), e);
        } finally {
            session.close();
        }
    }
}
