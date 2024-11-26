package com.group.mesh.db.repository;

import com.group.mesh.db.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    @Query(nativeQuery = true, value = "select exists(select true " +
            "              from email_data " +
            "              where user_id = :userId " +
            "              group by user_id " +
            "              having count(*) > 1)")
    boolean hasUserMoreThanOneEmail(Long userId);

    void deleteByEmail(String email);

    @Query(nativeQuery = true, value = "update email_data " +
            "set email = :newEmail " +
            "where email = :oldEmail " +
            "  and user_id = :userId " +
            "returning email")
    Optional<String> replaceEmail(String oldEmail, String newEmail, Long userId);

    @Query(nativeQuery = true, value = "select user_id from email_data where email = :email")
    Optional<Long> getUserIdByEmail(String email);
}
