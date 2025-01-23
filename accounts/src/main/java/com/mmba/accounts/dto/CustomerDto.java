package com.mmba.accounts.dto;

import com.mmba.accounts.validation.ValidEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Customer",
        description = "Schema to hold customer and account information"
)
public class CustomerDto {

    @Schema(
            description = "name of the customer",
            example = "Mashak Balaganoor"
    )
    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 30, message = "name should have at least 2 characters and at most 30 characters")
    private String name;

    @Schema(
            description = "Customer email address",
            example = "mashakb123@gmail.com"
    )
    @ValidEmail
    @NotEmpty(message = "email should not be empty")
    private String email;

    @Schema(
            description = "Customer Mobile Number",
            example = "6363220202"
    )
    @NotEmpty(message = "mobileNumber should not be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "mobileNumber should be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Customer Account details "
    )
    private AccountDto accountDto;
}
