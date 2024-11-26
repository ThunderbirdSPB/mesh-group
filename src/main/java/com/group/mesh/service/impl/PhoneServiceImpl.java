package com.group.mesh.service.impl;

import com.group.mesh.db.entity.Phone;
import com.group.mesh.db.entity.User;
import com.group.mesh.db.repository.PhoneRepository;
import com.group.mesh.service.PhoneService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    private PhoneRepository phoneRepository;

    @Override
    public void addPhone(Long userId, String phone) {
        phoneRepository.getUserIdByPhone(phone).ifPresent(id -> {
            if (id.equals(userId)) {
                throw new IllegalStateException("This user already has provided phone");
            }
            throw new IllegalStateException("Provided phone already taken");
        });
        val phoneEntity = Phone.builder()
                .phone(phone)
                .user(User.builder().id(userId).build())
                .build();
        phoneRepository.save(phoneEntity);
    }

    @Override
    public void deletePhone(Long userId, String phone) {
        if (phoneRepository.hasUserMoreThanOnePhone(userId)) {
            phoneRepository.deleteByPhone(phone);
        } else {
            throw new IllegalStateException(String
                    .format("phone = %s can't be deleted because user_id = %d has only one phone.", phone, userId));
        }
    }

    @Override
    public void replacePhone(Long userId, String oldPhone, String newPhone) {
        phoneRepository.replacePhone(oldPhone, newPhone, userId)
                .orElseThrow(() -> new IllegalStateException(String
                        .format("Failed to replace phone = %s for user_id = %d with phone = %s", oldPhone, userId, newPhone)));
    }
}
