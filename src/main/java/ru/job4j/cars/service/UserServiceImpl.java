package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.exception.EmailReservedException;
import ru.job4j.cars.exception.RepositoryException;
import ru.job4j.cars.exception.ServiceException;
import ru.job4j.cars.exception.UniqueViolationException;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.api.UserStore;
import ru.job4j.cars.service.api.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserStore userStore;

    public UserServiceImpl(UserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public User add(User user) {
        try {
            return userStore.add(user);
        } catch (UniqueViolationException e) {
            throw new EmailReservedException(e.getMessage(), e);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean replace(long id, User user) {
        return userStore.replace(id, user);
    }

    @Override
    public boolean delete(long id) {
        return userStore.delete(id);
    }

    @Override
    public List<User> findAll() {
        return userStore.findAll();
    }

    @Override
    public User findById(long id) {
        return userStore.findById(id);
    }

    @Override
    public Optional<User> findUserByEmailAndPwd(String email, String password) {
        return userStore.findUserByEmailAndPwd(email, password);
    }
}
