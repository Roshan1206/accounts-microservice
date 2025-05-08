package com.microservice.accounts.mapper;

import com.microservice.accounts.dto.NewCustomerDto;
import com.microservice.accounts.dto.ExistingCustomerDto;
import com.microservice.accounts.entity.Customer;

public class CustomerMapper {

    public static ExistingCustomerDto mapToCustomerDto(Customer customer, ExistingCustomerDto customerDto){
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(NewCustomerDto newCustomerDto, Customer customer){
        customer.setName(newCustomerDto.getName());
        customer.setEmail(newCustomerDto.getEmail());
        customer.setMobileNumber(newCustomerDto.getMobileNumber());
        return customer;
    }
}
