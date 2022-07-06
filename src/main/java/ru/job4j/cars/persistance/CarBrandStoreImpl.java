package ru.job4j.cars.persistance;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.persistance.api.CarBrandStore;
import ru.job4j.cars.persistance.api.GenericPersistence;

import java.util.List;

@Repository
public class CarBrandStoreImpl extends GenericPersistence implements CarBrandStore {

    public CarBrandStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CarBrand add(CarBrand carBrand) {
        return genericPersist(session -> {
            session.save(carBrand);
            return carBrand;
        });
    }

    @Override
    public boolean replace(int id, CarBrand carBrand) {
        return genericPersist(session ->
                session.createQuery("update CarBrand cb set cb.name= :newName where cb.id = :cbId")
                        .setParameter("newName", carBrand.getName())
                        .setParameter("cbId", carBrand.getId())
                        .executeUpdate() > 0
        );
    }

    @Override
    public boolean delete(int id) {
        return genericPersist(session ->
                session.createQuery("delete from CarBrand cb where cb.id = :cbId")
                        .setParameter("cbId", id)
                        .executeUpdate() > 0
        );
    }

    @Override
    public List<CarBrand> findAll() {
        return genericPersist(session -> session.createQuery("from CarBrand ").list());
    }

    @Override
    public CarBrand findById(int id) {
        return genericPersist(session -> (CarBrand) session.createQuery("from CarBrand cb where cb.id = :cbId")
                .setParameter("cbId", id)
                .uniqueResult());
    }
}
