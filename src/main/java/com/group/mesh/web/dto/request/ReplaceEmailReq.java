package com.group.mesh.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Email;

@Value
@Builder
@Jacksonized
public class ReplaceEmailReq {
    @Email
    @JsonProperty("oldEmail")
    String oldEmail;

    @Email
    @JsonProperty("newEmail")
    String newEmail;
}
