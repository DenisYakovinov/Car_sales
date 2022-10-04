package ru.job4j.cars.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.api.UserStore;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserStore userStore;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void findById_shouldReturnUser_whenUserId() {
        User expected = new User();
        Mockito.when(userStore.findById(1)).thenReturn(expected);
        assertEquals(expected, userService.findById(1));
    }
}
