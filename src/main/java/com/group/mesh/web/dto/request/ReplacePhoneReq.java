package com.group.mesh.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@Jacksonized
public class ReplacePhoneReq {
    @NotBlank
    @JsonProperty("oldPhone")
    String oldPhone;

    @NotBlank
    @JsonProperty("newPhone")
    String newPhone;
}
