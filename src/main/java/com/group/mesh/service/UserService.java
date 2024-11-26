package com.group.mesh.service;

import com.group.mesh.db.entity.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for working with user
 */
public interface UserService {
    /**
     * Find users with birthdate after provided date.
     * @param page Defines a page that needs to be returned
     * @param dateTime Date of birth from which users should be returned
     * @return Returns users which correspond to query
     */
    List<User> getUsersByDateOfBirth(int page, int size, LocalDateTime dateTime);

    /**
     * Find user with provided phone.
     * @param page Defines a page that needs to be returned
     * @param phone The phone number by which to search for users
     * @return Returns user which correspond to query
     */
    User getUserByPhone(String phone);

    /**
     * Find user with provided email.
     * @param page Defines a page that needs to be returned
     * @param email Email by which to search for users
     * @return Returns user which correspond to query
     */
    User getUserByEmail(String email);

    /**
     * Find users with name like argument.
     * @param page Defines a page that needs to be returned
     * @param name String value by which to search for users
     * @return Returns user which correspond to query
     */
    List<User> getUsersByName(int page, int size, String name);
}
