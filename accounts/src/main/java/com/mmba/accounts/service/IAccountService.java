package com.mmba.accounts.service;

import com.mmba.accounts.dto.CustomerDto;

public interface IAccountService {

    /* Create an account
    *
    * @param customerDto = CustomerDto object
    * */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
