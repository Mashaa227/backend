package com.mmba.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @NotEmpty(message = "accountNumber should not be empty")
    @Pattern(regexp = "^[0-9]*$", message = "accountNumber should be numeric")
    private Long accountNumber;

    @NotEmpty
    private String accountType;

    @NotEmpty(message = "branchAddress should not be empty")
    private String branchAddress;


}
