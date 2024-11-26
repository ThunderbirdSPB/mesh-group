package com.group.mesh.db.repository;

import com.group.mesh.db.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    @Query(nativeQuery = true, value = "select exists(select true " +
            "              from phone_data " +
            "              where user_id = :userId " +
            "              group by user_id " +
            "              having count(*) > 1)")
    boolean hasUserMoreThanOnePhone(Long userId);

    void deleteByPhone(String phone);

    @Query(nativeQuery = true, value = "update phone_data " +
            "set phone = :newPhone " +
            "where phone = :oldPhone " +
            "  and user_id = :userId " +
            "returning phone")
    Optional<String> replacePhone(@Param("oldPhone") String oldPhone, @Param("newPhone") String newPhone, @Param("userId") Long userId);

    @Query(nativeQuery = true, value = "select user_id from phone_data where phone = :phone")
    Optional<Long> getUserIdByPhone(String phone);
}
