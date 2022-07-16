package ru.job4j.cars.repository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.exception.PersistenceException;
import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.repository.api.CarBrandStore;
import ru.job4j.cars.repository.api.GenericPersistence;

import java.util.List;

@Repository
public class CarBrandStoreImpl extends GenericPersistence implements CarBrandStore {

    public CarBrandStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CarBrand add(CarBrand carBrand) {
        try {
            return genericPersist(session -> {
                session.persist(carBrand);
                return carBrand;
            });
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't add a car brand (%s)", e.getMessage()), e);
        }
    }

    @Override
    public boolean replace(long id, CarBrand carBrand) {
        try {
            return genericPersist(session ->
                    session.createQuery("update CarBrand cb set cb.name= :newName where cb.id = :cbId")
                            .setParameter("newName", carBrand.getName())
                            .setParameter("cbId", carBrand.getId())
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't replace the car brand with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            return genericPersist(session ->
                    session.createQuery("delete from CarBrand cb where cb.id = :cbId")
                            .setParameter("cbId", id)
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't delete the car brand with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public List<CarBrand> findAll() {
        try {
            return genericPersist(session -> session.createQuery("from CarBrand ").list());
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't find all car brands (%s)", e.getMessage()), e);
        }
    }

    @Override
    public CarBrand findById(long id) {
        try {
            return genericPersist(session -> (CarBrand) session.createQuery("from CarBrand cb where cb.id = :cbId")
                    .setParameter("cbId", id)
                    .uniqueResult());
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't find the car brand with id = %s (%s)", id, e.getMessage()), e);
        }
    }
}
