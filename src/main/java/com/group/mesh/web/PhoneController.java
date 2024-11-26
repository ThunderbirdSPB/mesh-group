package com.group.mesh.web;

import com.group.mesh.config.log.IntegrationLog;
import com.group.mesh.service.PhoneService;
import com.group.mesh.web.dto.BaseResponseV1Dto;
import com.group.mesh.web.dto.request.ReplacePhoneReq;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping(value = "api/v1/user/phone")
@RequiredArgsConstructor
public class PhoneController {
    private final PhoneService phoneService;

    @PostMapping
    @IntegrationLog
    public BaseResponseV1Dto addPhone(@NotBlank String phone) {
        phoneService.addPhone(1L, phone);

        return BaseResponseV1Dto.prepareSuccessResponse();
    }

    @DeleteMapping
    @IntegrationLog
    public BaseResponseV1Dto deletePhone(@NotBlank String phone) {
        phoneService.deletePhone(1L, phone);

        return BaseResponseV1Dto.prepareSuccessResponse();
    }

    @PatchMapping
    @IntegrationLog
    public BaseResponseV1Dto replacePhone(@Valid @RequestBody ReplacePhoneReq dto) {
        phoneService.replacePhone(1L, dto.getOldPhone(), dto.getNewPhone());

        return BaseResponseV1Dto.prepareSuccessResponse();
    }
}
