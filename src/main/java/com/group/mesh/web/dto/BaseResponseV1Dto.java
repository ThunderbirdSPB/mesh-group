package com.group.mesh.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class BaseResponseV1Dto {
    @JsonProperty
    Status status;

    @JsonProperty("error")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String error;

    public static BaseResponseV1Dto prepareSuccessResponse() {
        return BaseResponseV1Dto.builder()
                .status(Status.SUCCESS)
                .build();
    }

    public static BaseResponseV1Dto prepareErrorResponse(String description) {
        return BaseResponseV1Dto.builder()
                .status(Status.ERROR)
                .error(description)
                .build();
    }

    public enum Status {
        SUCCESS, ERROR
    }
}
