package com.group.mesh.service;

import java.math.BigDecimal;

/**
 * Service for transferring money
 */
public interface TransferService {
    /**
     * Transfer money
     * @param userIdFrom User from whom transfer money
     * @param userIdTo User to whom transfer money
     * @param value Amount of transferring money
     */
    void transfer(Long userIdFrom, Long userIdTo, BigDecimal value);
}
