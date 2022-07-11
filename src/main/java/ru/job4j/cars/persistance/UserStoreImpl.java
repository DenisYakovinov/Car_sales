package ru.job4j.cars.persistance;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;
import ru.job4j.cars.persistance.api.GenericPersistence;
import ru.job4j.cars.persistance.api.UserStore;

import java.util.List;
import java.util.Optional;

@Repository
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
    public boolean replace(long id, User user) {
        return genericPersist(session ->
                session.createQuery("update User u set u.name= :newName, u.password = :uPass, u.email = :uMail,"
                                + "u.phone = :uPhone where u.id = :uId")
                        .setParameter("newName", user.getName())
                        .setParameter("uMail", user.getEmail())
                        .setParameter("uPhone", user.getPhone())
                        .setParameter("uId", user.getId())
                        .executeUpdate() > 0
        );
    }

    @Override
    public boolean delete(long id) {
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
    public User findById(long id) {
        return genericPersist(session -> (User) session.createQuery("from User u where u.id = :uId")
                .setParameter("uId", id)
                .uniqueResult());
    }

    @Override
    public Optional<User> findUserByEmailAndPwd(String email, String password) {
        return genericPersist(session ->
                session.createQuery("from User u where u.email = :uEmail and u.password = :uPassword")
                        .setParameter("uEmail", email)
                        .setParameter("uPassword", password)
                        .uniqueResultOptional()
        );
    }
}
