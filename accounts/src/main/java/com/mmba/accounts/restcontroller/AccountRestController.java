package com.mmba.accounts.restcontroller;

import com.mmba.accounts.constants.AccountConstant;
import com.mmba.accounts.dto.CustomerDto;
import com.mmba.accounts.dto.ErrorResponseDto;
import com.mmba.accounts.dto.ResponseDto;
import com.mmba.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Account CRUD rest Api endpoint",
    description = "Account related apis"
)
@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountRestController {

    private IAccountService accountService;

    @Operation(
        summary = "Create an account",
        description = "REST Api to create an account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
    }


    @Operation(
            summary = "Fetch an account",
            description = "REST Api to fetch an account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam("mobileNumber") @Pattern(regexp = "[0-9]{10}",message = "Mobile number should be 10 digits")
                                                               String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.fetchAccount(mobileNumber));

    }

    @Operation(
            summary = "Update an account",
            description = "REST Api to update an account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.updateAccount(customerDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
    }



    @Operation(
            summary = "Delete an account",
            description = "REST Api to delete an account"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Http status Ok"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Http Status Internal server Error"
        )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam("mobileNumber") @Pattern(regexp = "[0-9]{10}",message = "Mobile number should be 10 digits" )
                                                         String mobileNumber) {
        accountService.deleteAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
    }
}
