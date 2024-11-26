package com.group.mesh.web;

import com.group.mesh.config.log.IntegrationLog;
import com.group.mesh.service.EmailService;
import com.group.mesh.web.dto.BaseResponseV1Dto;
import com.group.mesh.web.dto.request.ReplaceEmailReq;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Validated
@RestController
@RequestMapping(value = "api/v1/user/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping
    @IntegrationLog
    public BaseResponseV1Dto addEmail(@Email String email) {
        emailService.addEmail(1L, email);

        return BaseResponseV1Dto.prepareSuccessResponse();
    }

    @DeleteMapping
    @IntegrationLog
    public BaseResponseV1Dto deleteEmail(@Email String email) {
        emailService.deleteEmail(1L, email);

        return BaseResponseV1Dto.prepareSuccessResponse();
    }

    @PatchMapping
    @IntegrationLog
    public BaseResponseV1Dto replaceEmail(@Valid @RequestBody ReplaceEmailReq dto) {
        emailService.replaceEmail(1L, dto.getOldEmail(), dto.getNewEmail());

        return BaseResponseV1Dto.prepareSuccessResponse();
    }
}
