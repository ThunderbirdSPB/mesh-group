package com.group.mesh.db.repository;

import com.group.mesh.db.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByDateOfBirthAfter(LocalDateTime dateTime, Pageable pageable);

    @Query(nativeQuery = true, value = "select u.* " +
            "from \"user\" u " +
            "         inner join phone_data pd on u.id = pd.user_id " +
            "where pd.phone = :phone")
    User findByPhone(String phone);

    @Query(nativeQuery = true, value = "select u.* " +
            "from \"user\" u " +
            "         inner join email_data ed on u.id = ed.user_id " +
            "where ed.email = :email")
    User findByEmail(String email);

    List<User> findByNameLike(String name, Pageable pageable);
}
