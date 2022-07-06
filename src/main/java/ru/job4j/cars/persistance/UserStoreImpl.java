package ru.job4j.cars.persistance;


import org.hibernate.SessionFactory;
import ru.job4j.cars.model.User;
import ru.job4j.cars.persistance.api.GenericPersistence;
import ru.job4j.cars.persistance.api.Store;
import ru.job4j.cars.persistance.api.UserStore;

import java.util.List;

public class UserStoreImpl extends GenericPersistence implements UserStore {

    public UserStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User add(User user) {
        return genericPersist(session -> {
            session.save(user);
            return user;
        });
    }

    @Override
    public boolean replace(int id, User user) {
        return genericPersist(session ->
                session.createQuery("update User u set u.name= :newName where u.id = :uId")
                        .setParameter("newName", user.getName())
                        .setParameter("uId", user.getId())
                        .executeUpdate() > 0
        );
    }

    @Override
    public boolean delete(int id) {
        return genericPersist(session ->
                session.createQuery("delete from User u where u.id = :uId")
                        .setParameter("uId", id)
                        .executeUpdate() > 0
        );
    }

    @Override
    public List<User> findAll() {
        return genericPersist(session -> session.createQuery("from User").list());
    }

    @Override
    public User findById(int id) {
        return genericPersist(session -> (User) session.createQuery("from User u where u.id = :uId")
                .setParameter("uId", id)
                .uniqueResult());
    }
}
