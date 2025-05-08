package com.microservice.accounts.service;

import com.microservice.accounts.dto.NewCustomerDto;
import com.microservice.accounts.dto.ExistingCustomerDto;

public interface IAccountsService {

    void createAccount(NewCustomerDto newCustomerDto);

    ExistingCustomerDto fetchAccountDetails(String mobileNumber);

    boolean updateAccount(ExistingCustomerDto updateExistingCustomerDto);

    boolean deleteAccount(String mobileNumber);
}
