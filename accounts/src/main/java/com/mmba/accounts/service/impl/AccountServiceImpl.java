package com.mmba.accounts.service.impl;

import com.mmba.accounts.constants.AccountConstant;
import com.mmba.accounts.dto.AccountDto;
import com.mmba.accounts.dto.CustomerDto;
import com.mmba.accounts.entity.Account;
import com.mmba.accounts.entity.Customer;
import com.mmba.accounts.exception.CustomerAlreadyExistsException;
import com.mmba.accounts.exception.ResourceNotFoundException;
import com.mmba.accounts.mapper.AccountMapper;
import com.mmba.accounts.mapper.CustomerMapper;
import com.mmba.accounts.repository.AccountRepository;
import com.mmba.accounts.repository.CustomerRepository;
import com.mmba.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
//@RequiredArgsConstructor
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> byMobileNumber = customerRepository.findByMobileNumber(customer.getMobileNumber());

        if (byMobileNumber.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with this mobile number :" + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setCreatedBy("SYSTEM");
        Customer save = customerRepository.save(customer);

        accountRepository.save(createNewAccount(save));

    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccountDto();
        if(accountDto != null) {
            Account account = accountRepository.findById(accountDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountDto.getAccountNumber().toString()));
            AccountMapper.mapToAccount(accountDto, account);
            account =accountRepository.save(account);

            Long id = account.getCustomerId();
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", id.toString()));
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        customerRepository.deleteById(customer.getCustomerId());
        accountRepository.deleteByCustomerId(customer.getCustomerId());

        return true;
    }


    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());

        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

        account.setAccountNumber(randomAccountNumber);
        account.setAccountType(AccountConstant.SAVINGS);
        account.setBranchAddress(AccountConstant.ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("SYSTEM");
        return accountRepository.save(account);
    }
}
