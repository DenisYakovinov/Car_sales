package ru.job4j.cars.persistance;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.persistance.api.CarStore;
import ru.job4j.cars.persistance.api.GenericPersistence;

import java.util.List;

@Repository
public class CarStoreImpl extends GenericPersistence implements CarStore {

    public CarStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Car add(Car car) {
        return genericPersist(session -> {
            session.save(car);
            return car;
        });
    }

    @Override
    public boolean replace(int id, Car car) {
        return genericPersist(session ->
                session.createQuery("update Car c set c.name= :cName, c.carBrand = :cBrand, c.carBodyType = :cType,"
                                + " c.engine = :cEngine where c.id = :cId")
                        .setParameter("cName", car.getName())
                        .setParameter("cBrand", car.getCarBrand())
                        .setParameter("cType", car.getCarBodyType())
                        .setParameter("cEngine", car.getEngine())
                        .setParameter("cId", car.getId())
                        .executeUpdate() > 0
        );
    }

    @Override
    public Car replaceWithDrivers(Car car) {
        return genericPersist(session -> {
            session.update(car);
            return car;
        });
    }

    @Override
    public boolean delete(int id) {
        return genericPersist(session ->
                session.createQuery("delete from Car c where c.id = :cId")
                        .setParameter("cId", id)
                        .executeUpdate() > 0
        );
    }

    @Override
    public List<Car> findAll() {
        return genericPersist(session -> session.createQuery("select distinct c from Car c left join fetch c.drivers")
                .list());
    }

    @Override
    public Car findById(int id) {
        return genericPersist(session -> (Car) session.createQuery("select distinct c from Car c left join fetch"
                        + " c.drivers where c.id = :cId")
                .setParameter("cId", id)
                .uniqueResult());
    }
}
