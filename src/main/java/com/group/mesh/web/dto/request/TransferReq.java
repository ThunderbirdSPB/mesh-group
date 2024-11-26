package com.group.mesh.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@Builder
@Jacksonized
public class TransferReq {
    @NotNull
    @JsonProperty("userIdTo")
    Long userIdTo;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @JsonProperty("value")
    BigDecimal value;
}
