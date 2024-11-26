package com.group.mesh.db.repository;

import com.group.mesh.db.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "update account " +
            "set balance = balance + (:percentage * balance) / 100 " +
            "where balance + (:percentage * balance) / 100 < (initial_balance + (:upToBalancePercentage * initial_balance) / 100)")
    void increaseBalanceByNPercent(int percentage, int upToBalancePercentage);

    @Modifying
    @Query(nativeQuery = true, value = "update account " +
            "set balance = balance - :value " +
            "where user_id = :userIdFrom; " +
            "update account " +
            "set balance = balance + :amount " +
            "where user_id = :userIdTo;")
    void transfer(@Param("userIdFrom") Long userIdFrom, @Param("userIdTo") Long userIdTo, @Param("value") BigDecimal value);
}
