package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.api.EngineStore;

import java.util.List;

@Repository
public class EngineStoreImpl extends GenericPersistence implements EngineStore {

    public EngineStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Engine add(Engine engine) {
        return genericPersist(session -> {
            session.persist(engine);
            return engine;
        }, "Can't add an engine");
    }

    @Override
    public boolean replace(long id, Engine engine) {
        return genericPersist(session ->
                        session.createQuery("update Engine e set e.name= :newName where e.id = :eId")
                                .setParameter("newName", engine.getName())
                                .setParameter("eId", engine.getId())
                                .executeUpdate() > 0,
                String.format("Can't replace the engine with id = %s", id));
    }

    @Override
    public boolean delete(long id) {
        return genericPersist(session ->
                        session.createQuery("delete from Engine e where e.id = :eId")
                                .setParameter("eId", id)
                                .executeUpdate() > 0,
                String.format("Can't delete the engine with id = %s", id));
    }

    @Override
    public List<Engine> findAll() {
        return genericPersist(session -> session.createQuery("from Engine ").list(),
                "Can't find all engines");
    }

    @Override
    public Engine findById(long id) {
        return genericPersist(session -> (Engine) session.createQuery("from Engine e where e.id = :eId")
                        .setParameter("eId", id)
                        .uniqueResult(),
                String.format("Can't find the engine with id = %s", id));
    }
}
