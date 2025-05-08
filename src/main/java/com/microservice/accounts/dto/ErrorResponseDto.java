package com.microservice.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name = "Error", description = "Schema to hold errors")
public class ErrorResponseDto {

    @Schema(description = "API path")
    private String apiPath;

    @Schema(description = "HTTP status code")
    private HttpStatus statusCode;

    @Schema(description = "Error message")
    private String errorMsg;

    @Schema(description = "Error time")
    private LocalDateTime errorTime;
}
