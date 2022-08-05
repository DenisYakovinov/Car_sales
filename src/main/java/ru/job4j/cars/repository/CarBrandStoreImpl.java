package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.repository.api.CarBrandStore;

import java.util.List;

@Repository
public class CarBrandStoreImpl extends GenericPersistence implements CarBrandStore {

    public CarBrandStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CarBrand add(CarBrand carBrand) {
        return genericPersist(session -> {
            session.persist(carBrand);
            return carBrand;
        }, "Can't add a car brand");
    }

    @Override
    public boolean replace(long id, CarBrand carBrand) {
        return genericPersist(session ->
                        session.createQuery("update CarBrand cb set cb.name= :newName where cb.id = :cbId")
                                .setParameter("newName", carBrand.getName())
                                .setParameter("cbId", carBrand.getId())
                                .executeUpdate() > 0,
                String.format("Can't replace the car brand with id = %s", id));
    }

    @Override
    public boolean delete(long id) {
        return genericPersist(session ->
                        session.createQuery("delete from CarBrand cb where cb.id = :cbId")
                                .setParameter("cbId", id)
                                .executeUpdate() > 0,
                String.format("Can't delete the car brand with id = %s", id));
    }

    @Override
    public List<CarBrand> findAll() {
        return genericPersist(session -> session.createQuery("from CarBrand ").list(),
                "Can't find all car brands ");
    }

    @Override
    public CarBrand findById(long id) {
            return genericPersist(session -> (CarBrand) session.createQuery("from CarBrand cb where cb.id = :cbId")
                    .setParameter("cbId", id)
                    .uniqueResult(),
                    String.format("Can't find the car brand with id = %s", id));
    }
}
