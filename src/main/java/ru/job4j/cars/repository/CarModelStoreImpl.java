package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.repository.api.CarModelStore;

import java.util.List;

@Repository
public class CarModelStoreImpl extends GenericPersistence implements CarModelStore {

    public CarModelStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CarModel add(CarModel carModel) {
        return genericPersist(session -> {
            session.persist(carModel);
            return carModel;
        }, "Can't add a car model");
    }

    @Override
    public boolean replace(long id, CarModel carModel) {
        return genericPersist(session ->
                        session.createQuery("update CarModel c set c.name = :cModel, c.carBrand = :cBrand, c.carBodyType = :cType"
                                        + "  where c.id = :cId")
                                .setParameter("cModel", carModel.getName())
                                .setParameter("cBrand", carModel.getCarBrand())
                                .setParameter("cType", carModel.getCarBodyType())
                                .setParameter("cId", carModel.getId())
                                .executeUpdate() > 0,
                String.format("Can't replace the car model with id = %s", id));
    }

    @Override
    public CarModel replaceWithEngines(CarModel carModel) {
        return genericPersist(session -> {
            session.update(carModel);
            return carModel;
        }, String.format("Can't replace the car model with engines and id = %s", carModel.getId()));
    }

    @Override
    public boolean delete(long id) {
        return genericPersist(session ->
                        session.createQuery("delete from CarModel c where c.id = :cId")
                                .setParameter("cId", id)
                                .executeUpdate() > 0,
                String.format("Can't delete the car model with id = %s", id));
    }

    @Override
    public List<CarModel> findAll() {
        return genericPersist(session -> session.
                        createQuery("select distinct c from CarModel c left join fetch c.engines").list(),
                "Can't find all car models");
    }

    @Override
    public List<CarModel> findAllByBrand(long brandId) {
        return genericPersist(session ->
                        session.createQuery("select c from CarModel c where c.carBrand.id = :bId")
                                .setParameter("bId", brandId)
                                .list(),
                String.format("Can't find all car models by brand with brand id = %s", brandId));
    }

    @Override
    public CarModel findById(long id) {
        return genericPersist(session -> (CarModel) session.createQuery("select c from CarModel c"
                                + " left join fetch c.engines where c.id = :cId")
                        .setParameter("cId", id)
                        .uniqueResult(),
                String.format("Can't find the car model with id = %s", id));
    }
}
