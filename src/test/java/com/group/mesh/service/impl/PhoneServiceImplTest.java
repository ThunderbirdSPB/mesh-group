package com.group.mesh.service.impl;

import com.group.mesh.db.entity.Phone;
import com.group.mesh.db.repository.PhoneRepository;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.group.mesh.TestUtils.PHONE;
import static com.group.mesh.TestUtils.USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhoneServiceImplTest {
    @Mock
    private PhoneRepository repository;
    @Captor
    private ArgumentCaptor<Phone> phoneCaptor;

    @InjectMocks
    private PhoneServiceImpl phoneService;

    @Test
    @DisplayName("If phone is not taken - add to user")
    void addPhoneSuccessfulTest() {
        // given
        when(repository.getUserIdByPhone(PHONE)).thenReturn(Optional.empty());

        // when
        phoneService.addPhone(USER_ID, PHONE);

        // then
        verify(repository).save(phoneCaptor.capture());
        val savedPhone = phoneCaptor.getValue();

        assertAll(
                () -> assertThat(savedPhone.getPhone()).isEqualTo(PHONE),
                () -> assertThat(savedPhone.getUser().getId()).isEqualTo(USER_ID)
        );
    }

    @Test
    @DisplayName("If user already has this phone - error")
    void addPhone_withUserAlreadyHavingThisPhone_throwError() {
        // given
        when(repository.getUserIdByPhone(PHONE)).thenReturn(Optional.of(USER_ID));

        // when then
        assertThatThrownBy(() -> phoneService.addPhone(USER_ID, PHONE)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("If phone already taken by some user - error")
    void addPhone_withAlreadyExistingPhone_throwError() {
        // given
        when(repository.getUserIdByPhone(PHONE)).thenReturn(Optional.of(Long.MAX_VALUE));

        // when then
        assertThatThrownBy(() -> phoneService.addPhone(USER_ID, PHONE)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("If user has more than one phone - delete")
    void deletePhoneSuccessfulTest() {
        // given
        when(repository.hasUserMoreThanOnePhone(USER_ID)).thenReturn(true);

        // when
        phoneService.deletePhone(USER_ID, PHONE);

        // then
        verify(repository).deleteByPhone(PHONE);
    }

    @Test
    @DisplayName("If user has only one phone - error")
    void deletePhone_withUserHasOnlyOnePhone_error() {
        // given
        when(repository.hasUserMoreThanOnePhone(USER_ID)).thenReturn(false);

        // when then
        assertThatThrownBy(() -> phoneService.deletePhone(USER_ID, PHONE)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("If user has provided old phone - replace")
    void replacePhoneSuccessfulTest() {
        // given
        when(repository.replacePhone(PHONE, PHONE, USER_ID)).thenReturn(Optional.of(PHONE));

        // when
        phoneService.replacePhone(USER_ID, PHONE, PHONE);

        // then
        verify(repository).replacePhone(PHONE, PHONE, USER_ID);
    }

    @Test
    @DisplayName("If old phone can not be replaced - error")
    void replacePhone_phoneCannotBeReplaced_error() {
        // given
        when(repository.replacePhone(PHONE, PHONE, USER_ID)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> phoneService.replacePhone(USER_ID, PHONE, PHONE))
                .isInstanceOf(IllegalStateException.class);
    }
}