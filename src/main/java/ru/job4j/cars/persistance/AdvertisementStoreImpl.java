package ru.job4j.cars.persistance;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.persistance.api.AdvertisementStore;
import ru.job4j.cars.persistance.api.GenericPersistence;

import java.util.List;

@Repository
public class AdvertisementStoreImpl extends GenericPersistence implements AdvertisementStore {

    private static final String BASE_QUERY_SELECT_PART = "select distinct a from Advertisement a"
            + " join fetch a.car c join fetch c.drivers";

    public AdvertisementStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Advertisement add(Advertisement advertisement) {
        return genericPersist(session -> {
            session.save(advertisement);
            return advertisement;
        });
    }

    @Override
    public boolean replace(int id, Advertisement advertisement) {
        return genericPersist(session ->
                session.createQuery("update Advertisement a set a.description = :newDescr, a.car= :newCar,"
                                + "a.created = :newCr, a.isSold = :newSold, a.photo = :newPhoto where a.id = :aId")
                        .setParameter("newDescr", advertisement.getDescription())
                        .setParameter("newCar", advertisement.getCar())
                        .setParameter("newCr", advertisement.getCreated())
                        .setParameter("newSold", advertisement.isSold())
                        .setParameter("aId", advertisement.getId())
                        .executeUpdate() > 0
        );
    }

    @Override
    public boolean delete(int id) {
        return genericPersist(session ->
                session.createQuery("delete from Advertisement a where a.id = :aId")
                        .setParameter("aId", id)
                        .executeUpdate() > 0
        );
    }

    @Override
    public List<Advertisement> findAll() {
        return genericPersist(session -> session.createQuery("from Advertisement").list());
    }

    @Override
    public Advertisement findById(int id) {
        return genericPersist(session -> (Advertisement)
                session.createQuery("from Advertisement a where a.id = :aId")
                        .setParameter("aId", id)
                        .uniqueResult()
        );
    }

    public List<Advertisement> findAllForLastDay() {
        return genericPersist(session ->
                session.createQuery(String.format("%s%s", BASE_QUERY_SELECT_PART, " where a.created = current_date"))
                        .list()
        );
    }

    public List<Advertisement> findAllWithPhoto() {
        return genericPersist(session ->
                session.createQuery(String.format("%s%s", BASE_QUERY_SELECT_PART, " where a.photo <> null")).list()
        );
    }

    public List<Advertisement> findAllByBrand(CarBrand brand) {
        return genericPersist(session ->
                session.createQuery(String.format("%s%s", BASE_QUERY_SELECT_PART, "where c.carBrand.id = :cId"))
                        .setParameter("cId", brand.getId())
                        .list()
        );
    }
}