package com.microservice.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Schema to hold response")
public class ResponseDto {

    @Schema(description = "HTTP status code")
    private String statusCode;

    @Schema(description = "HTTP status message")
    private String statusMsg;
}
