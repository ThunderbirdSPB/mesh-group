package com.group.mesh.service.impl;

import com.group.mesh.db.repository.AccountRepository;
import com.group.mesh.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.CannotSerializeTransactionException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Retryable(CannotSerializeTransactionException.class)
    public void transfer(Long userIdFrom, Long userIdTo, BigDecimal value) {
        accountRepository.transfer(userIdFrom, userIdTo, value);
    }
}
