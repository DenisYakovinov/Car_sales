package ru.job4j.cars.persistance;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarBodyType;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.persistance.api.CarBodyTypeStore;
import ru.job4j.cars.persistance.api.GenericPersistence;

import java.util.List;

@Repository
public class CarBodyTypeStoreImpl extends GenericPersistence implements CarBodyTypeStore {

    public CarBodyTypeStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CarBodyType add(CarBodyType carBodyType) {
        return genericPersist(session -> {
            session.save(carBodyType);
            return carBodyType;
        });
    }

    @Override
    public boolean replace(long id, CarBodyType carBodyType) {
        return genericPersist(session ->
                session.createQuery("update CarBodyType ct set ct.name= :newName where ct.id = :cbId")
                        .setParameter("newName", carBodyType.getName())
                        .setParameter("cbId", carBodyType.getId())
                        .executeUpdate() > 0
        );
    }

    @Override
    public boolean delete(long id) {
        return genericPersist(session ->
                session.createQuery("delete from CarBodyType ct where ct.id = :ctId")
                        .setParameter("ctId", id)
                        .executeUpdate() > 0
        );
    }

    @Override
    public List<CarBodyType> findAll() {
        return genericPersist(session -> session.createQuery("from CarBodyType ").list());
    }

    @Override
    public CarBodyType findById(long id) {
        return genericPersist(session -> (CarBodyType) session.createQuery("from CarBodyType ct where ct.id = :ctId")
                .setParameter("ctId", id)
                .uniqueResult());
    }

    @Override
    public List<CarBodyType> findAllByCarModel(CarModel model) {
        return null;
    }
}
