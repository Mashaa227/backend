package com.mmba.accounts.dto;

import com.mmba.accounts.validation.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 30, message = "name should have at least 2 characters and at most 30 characters")
    private String name;

    @ValidEmail
    @NotEmpty(message = "email should not be empty")
    private String email;

    @NotEmpty(message = "mobileNumber should not be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "mobileNumber should be 10 digits")
    private String mobileNumber;

    private AccountDto accountDto;
}
