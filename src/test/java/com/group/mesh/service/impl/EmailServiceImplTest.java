package com.group.mesh.service.impl;

import com.group.mesh.db.entity.Email;
import com.group.mesh.db.repository.EmailRepository;
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

import static com.group.mesh.TestUtils.EMAIL;
import static com.group.mesh.TestUtils.USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {
    @Mock
    private EmailRepository repository;
    @Captor
    private ArgumentCaptor<Email> emailCaptor;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    @DisplayName("If email is not taken - add to user")
    void addEmailSuccessfulTest() {
        // given
        when(repository.getUserIdByEmail(EMAIL)).thenReturn(Optional.empty());

        // when
        emailService.addEmail(USER_ID, EMAIL);

        // then
        verify(repository).save(emailCaptor.capture());
        val savedEmail = emailCaptor.getValue();

        assertAll(
                () -> assertThat(savedEmail.getEmail()).isEqualTo(EMAIL),
                () -> assertThat(savedEmail.getUser().getId()).isEqualTo(USER_ID)
        );
    }

    @Test
    @DisplayName("If user already has this email - error")
    void addEmail_withUserAlreadyHavingThisEmail_throwError() {
        // given
        when(repository.getUserIdByEmail(EMAIL)).thenReturn(Optional.of(USER_ID));

        // when then
        assertThatThrownBy(() -> emailService.addEmail(USER_ID, EMAIL)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("If email already taken by some user - error")
    void addEmail_withAlreadyExistingEmail_throwError() {
        // given
        when(repository.getUserIdByEmail(EMAIL)).thenReturn(Optional.of(Long.MAX_VALUE));

        // when then
        assertThatThrownBy(() -> emailService.addEmail(USER_ID, EMAIL)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("If user has more than one email - delete")
    void deleteEmailSuccessfulTest() {
        // given
        when(repository.hasUserMoreThanOneEmail(USER_ID)).thenReturn(true);

        // when
        emailService.deleteEmail(USER_ID, EMAIL);

        // then
        verify(repository).deleteByEmail(EMAIL);
    }

    @Test
    @DisplayName("If user has only one email - error")
    void deleteEmail_withUserHasOnlyOneEmail_error() {
        // given
        when(repository.hasUserMoreThanOneEmail(USER_ID)).thenReturn(false);

        // when then
        assertThatThrownBy(() -> emailService.deleteEmail(USER_ID, EMAIL)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("If user has provided old email - replace")
    void replaceEmailSuccessfulTest() {
        // given
        when(repository.replaceEmail(EMAIL, EMAIL, USER_ID)).thenReturn(Optional.of(EMAIL));

        // when
        emailService.replaceEmail(USER_ID, EMAIL, EMAIL);

        // then
        verify(repository).replaceEmail(EMAIL, EMAIL, USER_ID);
    }

    @Test
    @DisplayName("If old email can not be replaced - error")
    void replaceEmail_emailCannotBeReplaced_error() {
        // given
        when(repository.replaceEmail(EMAIL, EMAIL, USER_ID)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> emailService.replaceEmail(USER_ID, EMAIL, EMAIL))
                .isInstanceOf(IllegalStateException.class);
    }
}