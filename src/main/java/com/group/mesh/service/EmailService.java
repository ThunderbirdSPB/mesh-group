package com.group.mesh.service;

/**
 * Service for working with email
 */
public interface EmailService {
    /**
     * Adds email to user.
     */
    void addEmail(Long userId, String email);

    /**
     * Delete user's email. User must have at least one email remained.
     */
    void deleteEmail(Long userId, String email);

    /**
     * Replace existing email to provided.
     */
    void replaceEmail(Long userId, String oldEmail, String newEmail);
}
