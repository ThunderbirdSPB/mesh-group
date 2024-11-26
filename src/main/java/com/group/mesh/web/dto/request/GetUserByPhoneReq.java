package com.group.mesh.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Value
@Getter
@Builder
@Jacksonized
public class GetUserByPhoneReq {
    @NotBlank
    @JsonProperty("phone")
    String phone;
}
