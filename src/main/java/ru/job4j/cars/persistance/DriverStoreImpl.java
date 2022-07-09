package ru.job4j.cars.persistance;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.persistance.api.DriverStore;
import ru.job4j.cars.persistance.api.GenericPersistence;

import java.util.List;

@Repository
public class DriverStoreImpl extends GenericPersistence implements DriverStore {

    public DriverStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Driver add(Driver driver) {
        return genericPersist(session -> {
            session.save(driver);
            return driver;
        });
    }

    @Override
    public boolean replace(long id, Driver driver) {
        return genericPersist(session ->
                session.createQuery("update Driver d set d.name= :newName where d.id = :dId")
                        .setParameter("newName", driver.getName())
                        .setParameter("dId", driver.getId())
                        .executeUpdate() > 0
        );
    }

    @Override
    public boolean delete(long id) {
        return genericPersist(session ->
                session.createQuery("delete from Driver d where d.id = :dId")
                        .setParameter("dId", id)
                        .executeUpdate() > 0
        );
    }

    @Override
    public List<Driver> findAll() {
        return genericPersist(session -> session.createQuery("from Driver ").list());
    }

    @Override
    public Driver findById(long id) {
        return genericPersist(session -> (Driver) session.createQuery("from Driver d where d.id = :dId")
                .setParameter("dId", id)
                .uniqueResult());
    }
}
