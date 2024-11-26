package com.group.mesh.service.impl;

import com.group.mesh.db.entity.User;
import com.group.mesh.db.repository.UserRepository;
import com.group.mesh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getUsersByDateOfBirth(int page, int size, LocalDateTime dateTime) {
        return userRepository.findByDateOfBirthAfter(dateTime, PageRequest.of(page, size));
    }

    @Override
    public User getUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getUsersByName(int page, int size, String name) {
        return userRepository.findByNameLike(name, PageRequest.of(page, size));
    }
}
