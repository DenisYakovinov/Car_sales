package ru.job4j.cars.repository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.exception.PersistenceException;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.api.EngineStore;
import ru.job4j.cars.repository.api.GenericPersistence;

import java.util.List;

@Repository
public class EngineStoreImpl extends GenericPersistence implements EngineStore {

    public EngineStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Engine add(Engine engine) {
        try {
            return genericPersist(session -> {
                session.persist(engine);
                return engine;
            });
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't add an engine  (%s)", e.getMessage()), e);
        }
    }

    @Override
    public boolean replace(long id, Engine engine) {
        try {
            return genericPersist(session ->
                    session.createQuery("update Engine e set e.name= :newName where e.id = :eId")
                            .setParameter("newName", engine.getName())
                            .setParameter("eId", engine.getId())
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't replace the engine with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            return genericPersist(session ->
                    session.createQuery("delete from Engine e where e.id = :eId")
                            .setParameter("eId", id)
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't delete the engine with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public List<Engine> findAll() {
        try {
            return genericPersist(session -> session.createQuery("from Engine ").list());
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't find all engines (%s)", e.getMessage()), e);
        }
    }

    @Override
    public Engine findById(long id) {
        try {
            return genericPersist(session -> (Engine) session.createQuery("from Engine e where e.id = :eId")
                    .setParameter("eId", id)
                    .uniqueResult());
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't find the engine with id = %s (%s)", id, e.getMessage()), e);
        }
    }
}
