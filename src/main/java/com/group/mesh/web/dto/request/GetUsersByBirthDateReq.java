package com.group.mesh.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Jacksonized
public class GetUsersByBirthDateReq extends PageableReq {
    @NotNull
    @JsonProperty("birthDate")
    LocalDateTime birthDate;

    @Builder
    public GetUsersByBirthDateReq(@NotNull Integer page, @NotNull Integer size, LocalDateTime birthDate) {
        super(page, size);
        this.birthDate = birthDate;
    }
}
