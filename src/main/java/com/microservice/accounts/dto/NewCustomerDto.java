package com.microservice.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer and Account information")
public class NewCustomerDto {

    @Schema(description = "Customer name", example = "John Doe")
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of customer name should be between 5 to 30")
    private String name;

    @Schema(description = "Customer email", example = "john@example.com")
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid email")
    private String email;

    @Schema(description = "Customer mobile number", example = "9887764422")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits")
    private String mobileNumber;
}
