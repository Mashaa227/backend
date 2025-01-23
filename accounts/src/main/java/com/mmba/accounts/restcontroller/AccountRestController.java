package com.mmba.accounts.restcontroller;

import com.mmba.accounts.constants.AccountConstant;
import com.mmba.accounts.dto.CustomerDto;
import com.mmba.accounts.dto.ResponseDto;
import com.mmba.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountRestController {

    private IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam("mobileNumber") @Pattern(regexp = "[0-9]{10}",message = "Mobile number should be 10 digits")
                                                               String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.fetchAccount(mobileNumber));

    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.updateAccount(customerDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam("mobileNumber") @Pattern(regexp = "[0-9]{10}",message = "Mobile number should be 10 digits" )
                                                         String mobileNumber) {
        accountService.deleteAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
    }
}
