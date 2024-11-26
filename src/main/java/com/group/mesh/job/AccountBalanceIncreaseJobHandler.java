package com.group.mesh.job;

import com.group.mesh.db.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountBalanceIncreaseJobHandler {
    private final AccountRepository accountRepository;

    @Value("${scheduler.increase-by-percent}")
    private Integer percentage;
    @Value("${scheduler.up-to-increase-percentage}")
    private Integer upToPercentage;

    @Transactional
    @Retryable(ObjectOptimisticLockingFailureException.class)
    public void increaseBalance() {
        try {
            log.info("Start increasing user balance by percentage = {}", percentage);
            accountRepository.increaseBalanceByNPercent(percentage, upToPercentage);
        } catch (Exception e) {
            log.error("Error while increasing user balance", e);
        }
    }
}
