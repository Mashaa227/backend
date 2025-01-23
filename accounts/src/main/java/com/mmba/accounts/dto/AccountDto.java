package com.mmba.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Account",
        description = "Schema to hold Account details"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @Schema(
            description = "Account Number of the customer"
    )
    @NotEmpty(message = "accountNumber should not be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "accountNumber should be numeric")
    private Long accountNumber;

    @Schema(
            description = "type of the account",
            example = "Saving or current"
    )
    @NotEmpty
    private String accountType;

    @Schema(
            description = "Bank location Details",
            example = "maruti nagar road more super market btm 1st stage,Bangalore"
    )
    @NotEmpty(message = "branchAddress should not be empty")
    private String branchAddress;


}
