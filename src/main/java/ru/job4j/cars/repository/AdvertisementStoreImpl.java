package ru.job4j.cars.repository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.exception.PersistenceException;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.repository.api.AdvertisementStore;
import ru.job4j.cars.repository.api.GenericPersistence;

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
        try {
            return genericPersist(session -> {
                session.persist(advertisement);
                return advertisement;
            });
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't add the ad (%s)", e.getMessage()), e);
        }
    }

    @Override
    public boolean replace(long id, Advertisement advertisement) {
        try {
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
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't replace the ad with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public Advertisement replaceWithPhotos(Advertisement advertisement) {
        try {
            return genericPersist(session -> {
                session.update(advertisement);
                return advertisement;
            });
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't replace the ad with photos and id = %s (%s)", advertisement.getId(),
                            e.getMessage()), e);
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            return genericPersist(session ->
                    session.createQuery("delete from Advertisement a where a.id = :aId")
                            .setParameter("aId", id)
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't delete the ad with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public List<Advertisement> findAll() {
        try {
            return genericPersist(session -> session.createQuery("from Advertisement").list());
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't find all advertisements (%s)", e.getMessage()), e);
        }
    }

    @Override
    public List<Advertisement> findAllWithPhotos() {
        try {
            return genericPersist(session -> session.createQuery("select distinct a from Advertisement a"
                    + " left join fetch a.photos order by a.created").list());
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't find all advertisements with photos (%s)", e.getMessage()), e);
        }
    }

    @Override
    public Advertisement findById(long id) {
        try {
            return genericPersist(session -> (Advertisement)
                    session.createQuery("select distinct a from Advertisement a join fetch a.photos"
                                    + " where a.id = :aId")
                            .setParameter("aId", id)
                            .getSingleResult()
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't find the advertisements with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    public List<Advertisement> findAllForLastDay() {
        try {
            return genericPersist(session ->
                    session.createQuery(String.format("%s%s", BASE_QUERY_SELECT_PART, " where a.created = current_date"))
                            .list()
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't find an advertisements for last (%s)", e.getMessage()), e);
        }
    }

    public List<Advertisement> findAllByBrand(CarBrand brand) {
        try {
            return genericPersist(session ->
                    session.createQuery(String.format("%s%s", BASE_QUERY_SELECT_PART, "where c.carBrand.id = :cId"))
                            .setParameter("cId", brand.getId())
                            .list()
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't find all advertisements by brand with bran id = %s (%s)",
                            brand.getId(), e.getMessage()), e);
        }
    }

    @Override
    public boolean setSoldById(long id) {
        try {
            return genericPersist(session ->
                    session.createQuery("update Advertisement a set a.isSold = :aSold where a.id = :aId")
                            .setParameter("aSold", true)
                            .setParameter("aId", id)
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't set sold by ad id = %s (%s)", id, e.getMessage()), e);
        }
    }
}