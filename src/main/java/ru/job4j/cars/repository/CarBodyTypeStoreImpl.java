package ru.job4j.cars.repository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.exception.PersistenceException;
import ru.job4j.cars.model.CarBodyType;
import ru.job4j.cars.repository.api.CarBodyTypeStore;
import ru.job4j.cars.repository.api.GenericPersistence;

import java.util.List;

@Repository
public class CarBodyTypeStoreImpl extends GenericPersistence implements CarBodyTypeStore {

    public CarBodyTypeStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CarBodyType add(CarBodyType carBodyType) {
        try {
            return genericPersist(session -> {
                session.persist(carBodyType);
                return carBodyType;
            });
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't add a car body type (%s)", e.getMessage()), e);
        }
    }

    @Override
    public boolean replace(long id, CarBodyType carBodyType) {
        try {
            return genericPersist(session ->
                    session.createQuery("update CarBodyType ct set ct.name= :newName where ct.id = :cbId")
                            .setParameter("newName", carBodyType.getName())
                            .setParameter("cbId", carBodyType.getId())
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't replace a car body type with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            return genericPersist(session ->
                    session.createQuery("delete from CarBodyType ct where ct.id = :ctId")
                            .setParameter("ctId", id)
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't delete a car body type with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public List<CarBodyType> findAll() {
        try {
            return genericPersist(session -> session.createQuery("from CarBodyType ").list());
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't find all car body types (%s)", e.getMessage()), e);
        }
    }

    @Override
    public CarBodyType findById(long id) {
        try {
            return genericPersist(session -> (CarBodyType) session.createQuery("from CarBodyType ct where ct.id = :ctId")
                    .setParameter("ctId", id)
                    .uniqueResult());
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't find a car body type with id = %s (%s)", id, e.getMessage()), e);
        }
    }
}
