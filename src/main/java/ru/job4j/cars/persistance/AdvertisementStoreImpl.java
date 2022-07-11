package ru.job4j.cars.persistance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.persistance.api.AdvertisementStore;
import ru.job4j.cars.persistance.api.GenericPersistence;

import java.util.List;

@Repository
public class AdvertisementStoreImpl extends GenericPersistence implements AdvertisementStore {

    private static final String BASE_QUERY_SELECT_PART = "select distinct a from Advertisement a"
            + " join fetch a.car";

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
    public boolean replace(long id, Advertisement advertisement) {
        return genericPersist(session ->
                session.createQuery("update Advertisement a set a.releaseDate = :aRelease, a.description = :aDescr,"
                                + " a.carModel= :aCar, a.created = :aCreated, a.isSold = :aSold, a.price = :aPrice,"
                                + "a.engine = :aEngine where a.id = :aId")
                        .setParameter("aRelease", advertisement.getReleaseDate())
                        .setParameter("aDescr", advertisement.getDescription())
                        .setParameter("aCar", advertisement.getCarModel())
                        .setParameter("aCreated", advertisement.getCreated())
                        .setParameter("aSold", advertisement.isSold())
                        .setParameter("aPrice", advertisement.getPrice())
                        .setParameter("aId", advertisement.getId())
                        .setParameter("aEngine", advertisement.getEngine())
                        .executeUpdate() > 0
        );
    }

    @Override
    public Advertisement replaceWithPhotos(Advertisement advertisement) {
        return genericPersist(session -> {
            session.update(advertisement);
            return advertisement;
        });
    }

    @Override
    public boolean delete(long id) {
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
    public List<Advertisement> findAllWithPhotos() {
        return genericPersist(session -> session.createQuery("select distinct a from Advertisement a"
                + " left join fetch a.photos order by a.created").list());
    }

    @Override
    public Advertisement findById(long id) {
        return genericPersist(session -> (Advertisement)
                session.createQuery("select distinct a from Advertisement a join fetch a.photos"
                                + " where a.id = :aId")
                        .setParameter("aId", id)
                        .getSingleResult()
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

    @Override
    public boolean setSoldById(long id) {
        return genericPersist(session ->
                session.createQuery("update Advertisement a set a.isSold = :aSold where a.id = :aId")
                        .setParameter("aSold", true)
                        .setParameter("aId", id)
                        .executeUpdate() > 0
        );
    }
}