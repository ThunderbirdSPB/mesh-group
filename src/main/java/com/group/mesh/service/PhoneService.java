package com.group.mesh.service;

/**
 * Service for working with phone
 */
public interface PhoneService {
    /**
     * Adds phone to user.
     */
    void addPhone(Long userId, String phone);

    /**
     * Delete user's phone. User must have at least one phone remained.
     */
    void deletePhone(Long userId, String phone);

    /**
     * Replace existing phone to provided.
     */
    void replacePhone(Long userId, String oldPhone, String newPhone);
}
