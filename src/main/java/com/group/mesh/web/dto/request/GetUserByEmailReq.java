package com.group.mesh.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Email;

@Value
@Getter
@Builder
@Jacksonized
public class GetUserByEmailReq {
    @Email
    @JsonProperty("email")
    String email;
}
