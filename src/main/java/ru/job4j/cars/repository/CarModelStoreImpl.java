package ru.job4j.cars.repository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.exception.PersistenceException;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.repository.api.CarModelStore;
import ru.job4j.cars.repository.api.GenericPersistence;

import java.util.List;

@Repository
public class CarModelStoreImpl extends GenericPersistence implements CarModelStore {

    public CarModelStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CarModel add(CarModel carModel) {
        try {
            return genericPersist(session -> {
                session.persist(carModel);
                return carModel;
            });
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't add a car model (%s)", e.getMessage()), e);
        }
    }

    @Override
    public boolean replace(long id, CarModel carModel) {
        try {
            return genericPersist(session ->
                    session.createQuery("update CarModel c set c.name = :cModel, c.carBrand = :cBrand, c.carBodyType = :cType"
                                    + "  where c.id = :cId")
                            .setParameter("cModel", carModel.getName())
                            .setParameter("cBrand", carModel.getCarBrand())
                            .setParameter("cType", carModel.getCarBodyType())
                            .setParameter("cId", carModel.getId())
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't replace the car model with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public CarModel replaceWithEngines(CarModel carModel) {
        try {
            return genericPersist(session -> {
                session.update(carModel);
                return carModel;
            });
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't replace the car model with engines and id = %s (%s)", carModel.getId(),
                            e.getMessage()), e);
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            return genericPersist(session ->
                    session.createQuery("delete from CarModel c where c.id = :cId")
                            .setParameter("cId", id)
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't delete the car model with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public List<CarModel> findAll() {
        try {
            return genericPersist(session -> session.
                    createQuery("select distinct c from CarModel c left join fetch c.engines").list());
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't find all car models (%s)", e.getMessage()), e);
        }
    }

    @Override
    public List<CarModel> findAllByBrand(long brandId) {
        try {
            return genericPersist(session ->
                    session.createQuery("select c from CarModel c where c.carBrand.id = :bId")
                            .setParameter("bId", brandId)
                            .list());
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't find all car models by brand with brand id = %s (%s)",
                    brandId, e.getMessage()), e);
        }
    }

    @Override
    public CarModel findById(long id) {
        try {
            return genericPersist(session -> (CarModel) session.createQuery("select c from CarModel c"
                            + " left join fetch c.engines where c.id = :cId")
                    .setParameter("cId", id)
                    .uniqueResult());
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't find the car model with id = %s (%s)", id, e.getMessage()), e);
        }
    }
}
