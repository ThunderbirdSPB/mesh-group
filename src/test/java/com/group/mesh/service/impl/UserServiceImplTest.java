package com.group.mesh.service.impl;

import com.group.mesh.db.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static com.group.mesh.TestUtils.CURRENT_DATE_TIME;
import static com.group.mesh.TestUtils.EMAIL;
import static com.group.mesh.TestUtils.NAME;
import static com.group.mesh.TestUtils.PHONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Check that repository called correctly")
    void getUsersByDateOfBirthTest() {
        // when
        userService.getUsersByDateOfBirth(0, 10, CURRENT_DATE_TIME);

        // then
        verify(userRepository).findByDateOfBirthAfter(eq(CURRENT_DATE_TIME), any(Pageable.class));
    }

    @Test
    @DisplayName("Check that repository called correctly")
    void getUserByPhoneTest() {
        // when
        userService.getUserByPhone(PHONE);

        // then
        verify(userRepository).findByPhone(PHONE);
    }

    @Test
    @DisplayName("Check that repository called correctly")
    void getUserByEmailTest() {
        // when
        userService.getUserByEmail(EMAIL);

        // then
        verify(userRepository).findByEmail(EMAIL);
    }

    @Test
    @DisplayName("Check that repository called correctly")
    void getUsersByNameTest() {
        // when
        userService.getUsersByName(0, 10, NAME);

        // then
        verify(userRepository).findByNameLike(eq(NAME), any(Pageable.class));
    }
}