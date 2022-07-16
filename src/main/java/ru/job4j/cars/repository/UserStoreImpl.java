package ru.job4j.cars.repository;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.exception.PersistenceException;
import ru.job4j.cars.exception.UniqueViolationException;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.api.GenericPersistence;
import ru.job4j.cars.repository.api.UserStore;

import java.util.List;
import java.util.Optional;

@Repository
public class UserStoreImpl extends GenericPersistence implements UserStore {

    public UserStoreImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User add(User user) {
        try {
            return genericPersist(session -> {
                session.persist(user);
                return user;
            });
        } catch (ConstraintViolationException e) {
            throw new UniqueViolationException(e.getMessage(), e);
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("User %s can't be added (%s)", user, e.getMessage()), e);
        }
    }

    @Override
    public boolean replace(long id, User user) {
        try {
            return genericPersist(session ->
                    session.createQuery("update User u set u.name= :newName, u.password = :uPass, u.email = :uMail,"
                                    + "u.phone = :uPhone where u.id = :uId")
                            .setParameter("newName", user.getName())
                            .setParameter("uMail", user.getEmail())
                            .setParameter("uPhone", user.getPhone())
                            .setParameter("uId", user.getId())
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't replace the user with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            return genericPersist(session ->
                    session.createQuery("delete from User u where u.id = :uId")
                            .setParameter("uId", id)
                            .executeUpdate() > 0
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't delete the user with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return genericPersist(session -> session.createQuery("from User").list());
        } catch (HibernateException e) {
            throw new PersistenceException(String.format("Can't find all users (%s)", e.getMessage()), e);
        }
    }

    @Override
    public User findById(long id) {
        try {
            return genericPersist(session -> (User) session.createQuery("from User u where u.id = :uId")
                    .setParameter("uId", id)
                    .uniqueResult());
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't find the user with id = %s (%s)", id, e.getMessage()), e);
        }
    }

    @Override
    public Optional<User> findUserByEmailAndPwd(String email, String password) {
        try {
            return genericPersist(session ->
                    session.createQuery("from User u where u.email = :uEmail and u.password = :uPassword")
                            .setParameter("uEmail", email)
                            .setParameter("uPassword", password)
                            .uniqueResultOptional()
            );
        } catch (HibernateException e) {
            throw new PersistenceException(
                    String.format("Can't find the user with email = %s (%s)", email, e.getMessage()), e);
        }
    }
}
