package com.group.mesh.job;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountBalanceIncreaseScheduler {
  private final AccountBalanceIncreaseJobHandler accountBalanceIncreaseJobHandler;

    @Scheduled(fixedDelayString = "${scheduler.push-delay-ms}")
    public void increaseBalance() {
        accountBalanceIncreaseJobHandler.increaseBalance();
    }
}
