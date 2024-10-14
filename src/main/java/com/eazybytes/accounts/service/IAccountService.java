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

    /**
     * @param customerDto
     * @return a boolean value indicating account is updated or not
     */
    boolean updateCustomer(CustomerDto customerDto);

    /**
     * @param accountNumber
     * @return return boolean value indicating account is deleted or not
     */
    boolean deleteCustomer(Long accountNumber);
}
