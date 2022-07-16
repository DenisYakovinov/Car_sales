package ru.job4j.cars.repository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.exception.PersistenceException;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.repository.api.GenericPersistence;
import ru.job4j.cars.repository.api.PhotoStore;

import java.util.List;

@Repository
public class PhotoStoreImpl extends GenericPersistence implements PhotoStore {

    public PhotoStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Photo add(Photo photo) {
        try {
            return genericPersist(session -> {
                session.persist(photo);
                return photo;
            });
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't add a photo brand (%s)", e.getMessage()), e);
        }
    }

    @Override
    public boolean replace(long id, Photo photo) {
        try {
            return genericPersist(session ->
                    session.createQuery("update Photo p set p.photo = :pPhoto where p.id = :pId")
                            .setParameter("pPhoto", photo.getPhoto())
                            .setParameter("pId", photo.getId())
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't replace the photo with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            return genericPersist(session ->
                    session.createQuery("delete from Photo p where p.id = :pId")
                            .setParameter("pId", id)
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't delete the photo with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public List<Photo> findAll() {
        try {
            return genericPersist(session -> session.createQuery("from Photo ").list());
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't find all photos (%s)", e.getMessage()), e);
        }
    }

    @Override
    public Photo findById(long id) {
        try {
            return genericPersist(session -> (Photo) session.createQuery("from Photo p where p.id = :pId")
                    .setParameter("pId", id)
                    .uniqueResult());
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't find the photo with id = %s (%s)", id, e.getMessage()), e);
        }
    }
}
