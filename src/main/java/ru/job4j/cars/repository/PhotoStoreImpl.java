package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.repository.api.PhotoStore;

import java.util.List;

@Repository
public class PhotoStoreImpl extends GenericPersistence implements PhotoStore {

    public PhotoStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Photo add(Photo photo) {
        return genericPersist(session -> {
            session.persist(photo);
            return photo;
        }, "Can't add a photo brand");
    }

    @Override
    public boolean replace(long id, Photo photo) {
        return genericPersist(session ->
                        session.createQuery("update Photo p set p.photo = :pPhoto where p.id = :pId")
                                .setParameter("pPhoto", photo.getPhoto())
                                .setParameter("pId", photo.getId())
                                .executeUpdate() > 0,
                String.format("Can't replace the photo with id = %s", id));
    }

    @Override
    public boolean delete(long id) {
        return genericPersist(session ->
                        session.createQuery("delete from Photo p where p.id = :pId")
                                .setParameter("pId", id)
                                .executeUpdate() > 0,
                String.format("Can't delete the photo with id = %s", id));
    }

    @Override
    public List<Photo> findAll() {
        return genericPersist(session -> session.createQuery("from Photo ").list(),
                "Can't find all photos");
    }

    @Override
    public Photo findById(long id) {
        return genericPersist(session -> (Photo) session.createQuery("from Photo p where p.id = :pId")
                        .setParameter("pId", id)
                        .uniqueResult(),
                String.format("Can't find the photo with id = %s", id));
    }
}
