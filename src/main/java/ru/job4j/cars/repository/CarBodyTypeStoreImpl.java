package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarBodyType;
import ru.job4j.cars.repository.api.CarBodyTypeStore;

import java.util.List;

@Repository
public class CarBodyTypeStoreImpl extends GenericPersistence implements CarBodyTypeStore {

    public CarBodyTypeStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CarBodyType add(CarBodyType carBodyType) {
        return genericPersist(session -> {
                    session.persist(carBodyType);
                    return carBodyType;
                },
                "Can't add a car body type");
    }

    @Override
    public boolean replace(long id, CarBodyType carBodyType) {
        return genericPersist(session ->
                        session.createQuery("update CarBodyType ct set ct.name= :newName where ct.id = :cbId")
                                .setParameter("newName", carBodyType.getName())
                                .setParameter("cbId", carBodyType.getId())
                                .executeUpdate() > 0,
                String.format("Can't replace a car body type with id = %s", id));
    }

    @Override
    public boolean delete(long id) {
        return genericPersist(session ->
                        session.createQuery("delete from CarBodyType ct where ct.id = :ctId")
                                .setParameter("ctId", id)
                                .executeUpdate() > 0,
                String.format("Can't delete a car body type with id = %s", id));
    }

    @Override
    public List<CarBodyType> findAll() {
        return genericPersist(session -> session.createQuery("from CarBodyType ").list(),
                "Can't find all car body types");
    }

    @Override
    public CarBodyType findById(long id) {
        return genericPersist(session -> (CarBodyType) session.createQuery("from CarBodyType ct where ct.id = :ctId")
                        .setParameter("ctId", id)
                        .uniqueResult(),
                String.format("Can't find a car body type with id = %s", id));
    }
}
