package com.group.mesh.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;

@Getter
@Jacksonized
@AllArgsConstructor
public class PageableReq {
    @NotNull
    @JsonProperty("page")
    Integer page;

    @NotNull
    @JsonProperty("size")
    Integer size;
}
