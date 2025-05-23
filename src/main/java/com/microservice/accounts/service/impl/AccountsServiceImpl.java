package com.microservice.accounts.service.impl;

import com.microservice.accounts.constants.AccountsConstant;
import com.microservice.accounts.dto.AccountsDto;
import com.microservice.accounts.dto.NewCustomerDto;
import com.microservice.accounts.dto.ExistingCustomerDto;
import com.microservice.accounts.entity.Accounts;
import com.microservice.accounts.entity.Customer;
import com.microservice.accounts.exception.CustomerAlreadyExistsException;
import com.microservice.accounts.exception.ResourceNotFoundException;
import com.microservice.accounts.mapper.AccountsMapper;
import com.microservice.accounts.mapper.CustomerMapper;
import com.microservice.accounts.repository.AccountsRepository;
import com.microservice.accounts.repository.CustomerRepository;
import com.microservice.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;

    /**
     * @param newCustomerDto
     */
    @Override
    public void createAccount(NewCustomerDto newCustomerDto) {
        Customer customer = CustomerMapper.mapToCustomer(newCustomerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(newCustomerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with give mobile number " + newCustomerDto.getMobileNumber());
        }
        customerRepository.save(customer);
        accountsRepository.save(createNewAccount(customer));

    }


    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstant.SAVINGS);
        newAccount.setBranchAddress(AccountsConstant.ADDRESS);
        return newAccount;
    }


    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public ExistingCustomerDto fetchAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        ExistingCustomerDto existingCustomerDto = CustomerMapper.mapToCustomerDto(customer, new ExistingCustomerDto());
        existingCustomerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
        return existingCustomerDto;
    }

    /**
     * @param updateExistingCustomerDto
     * @return
     */
    @Override
    public boolean updateAccount(ExistingCustomerDto updateExistingCustomerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = updateExistingCustomerDto.getAccountsDto();

        if(accountsDto != null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "Account number", accountsDto.getAccountNumber().toString())
            );
//            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accountsRepository.save(accounts);

            Long customerID = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerID).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customer id", customerID.toString())
            );
//            CustomerMapper.mapToCustomer(updateExistingCustomerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}
