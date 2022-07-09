package ru.job4j.cars.persistance;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.persistance.api.CarModelStore;
import ru.job4j.cars.persistance.api.GenericPersistence;

import java.util.List;

@Repository
public class CarModelStoreImpl extends GenericPersistence implements CarModelStore {

    public CarModelStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CarModel add(CarModel carModel) {
        return genericPersist(session -> {
            session.save(carModel);
            return carModel;
        });
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
                        .executeUpdate() > 0
        );
    }

    @Override
    public CarModel replaceWithEngines(CarModel carModel) {
        return genericPersist(session -> {
            session.update(carModel);
            return carModel;
        });
    }

    @Override
    public boolean delete(long id) {
        return genericPersist(session ->
                session.createQuery("delete from CarModel c where c.id = :cId")
                        .setParameter("cId", id)
                        .executeUpdate() > 0
        );
    }

    @Override
    public List<CarModel> findAll() {
        return genericPersist(session -> session.createQuery("select distinct c from CarModel c left join fetch c.engines")
                .list());
    }


    @Override
    public List<CarModel> findAllByBrand(long brandId) {
        return genericPersist(session ->
                session.createQuery("select c from CarModel c where c.carBrand.id = :bId")
                        .setParameter("bId", brandId)
                        .list());
    }

    @Override
    public CarModel findById(long id) {
        return genericPersist(session -> (CarModel) session.createQuery("select distinct c from CarModel c left join fetch"
                        + " c.engines where c.id = :cId")
                .setParameter("cId", id)
                .uniqueResult());
    }
}
