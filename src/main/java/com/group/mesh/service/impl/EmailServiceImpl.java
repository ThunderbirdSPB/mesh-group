package com.group.mesh.service.impl;

import com.group.mesh.db.entity.Email;
import com.group.mesh.db.entity.User;
import com.group.mesh.db.repository.EmailRepository;
import com.group.mesh.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;

    @Override
    @Transactional
    public void addEmail(Long userId, String email) {
        emailRepository.getUserIdByEmail(email).ifPresent(id -> {
            if (id.equals(userId)) {
                throw new IllegalStateException("This user already has provided email");
            }
            throw new IllegalStateException("Provided email already taken");
        });
        val emailEntity = Email.builder()
                .email(email)
                .user(User.builder().id(userId).build())
                .build();
        emailRepository.save(emailEntity);
    }

    @Override
    @Transactional
    public void deleteEmail(Long userId, String email) {
        if (emailRepository.hasUserMoreThanOneEmail(userId)) {
            emailRepository.deleteByEmail(email);
        } else {
            throw new IllegalStateException(String
                    .format("Email = %s can't be deleted because user_id = %d has only one email.", email, userId));
        }
    }

    @Override
    @Transactional
    public void replaceEmail(Long userId, String oldEmail, String newEmail) {
        emailRepository.replaceEmail(oldEmail, newEmail, userId)
                .orElseThrow(() -> new IllegalStateException(String
                        .format("Failed to replace email = %s for user_id = %d with email = %s", oldEmail, userId, newEmail)));
    }
}
