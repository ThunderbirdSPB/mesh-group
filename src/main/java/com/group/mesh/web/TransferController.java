package com.group.mesh.web;

import com.group.mesh.config.log.IntegrationLog;
import com.group.mesh.service.TransferService;
import com.group.mesh.web.dto.BaseResponseV1Dto;
import com.group.mesh.web.dto.request.TransferReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequestMapping(value = "api/v1/user/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    @IntegrationLog
    public BaseResponseV1Dto transfer(@Valid @RequestBody TransferReq req) {
        log.info("Starting transferring money from user_id = {}, to user_id = {}", 1L, req.getUserIdTo());
        transferService.transfer(1L, req.getUserIdTo(), req.getValue());
        return BaseResponseV1Dto.prepareSuccessResponse();
    }
}
