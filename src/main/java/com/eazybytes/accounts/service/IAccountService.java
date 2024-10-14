package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     * @param   customerDto - Customer Object
      * */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber
     * @return customer details
     */
    CustomerDto getCustomerByMobileNumber(String mobileNumber);
}
