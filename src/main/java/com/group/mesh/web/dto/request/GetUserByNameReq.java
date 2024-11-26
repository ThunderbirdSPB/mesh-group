package com.group.mesh.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Jacksonized
public class GetUserByNameReq extends PageableReq {
    @NotBlank
    @JsonProperty("name")
    String name;

    @Builder
    public GetUserByNameReq(@NotNull Integer page, @NotNull Integer size, String name) {
        super(page, size);
        this.name = name;
    }
}
