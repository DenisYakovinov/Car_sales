package ru.job4j.cars.persistance;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.persistance.api.GenericPersistence;
import ru.job4j.cars.persistance.api.PhotoStore;

import java.util.List;

@Repository
public class PhotoStoreImpl extends GenericPersistence implements PhotoStore {

    public PhotoStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Photo add(Photo photo) {
        return genericPersist(session -> {
            session.save(photo);
            return photo;
        });
    }

    @Override
    public boolean replace(int id, Photo photo) {
        return genericPersist(session ->
                session.createQuery("update Photo p set p.photo = :pPhoto where p.id = :pId")
                        .setParameter("pPhoto", photo.getPhoto())
                        .setParameter("pId", photo.getId())
                        .executeUpdate() > 0
        );
    }

    @Override
    public boolean delete(int id) {
        return genericPersist(session ->
                session.createQuery("delete from Photo p where p.id = :pId")
                        .setParameter("pId", id)
                        .executeUpdate() > 0
        );
    }

    @Override
    public List<Photo> findAll() {
        return genericPersist(session -> session.createQuery("from Photo ").list());
    }

    @Override
    public Photo findById(int id) {
        return genericPersist(session -> (Photo) session.createQuery("from Photo p where p.id = :pId")
                .setParameter("pId", id)
                .uniqueResult());
    }
}
