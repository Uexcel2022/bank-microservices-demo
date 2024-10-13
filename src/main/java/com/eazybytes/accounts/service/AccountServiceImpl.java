package com.eazybytes.accounts.service;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Account;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerExistException;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    /**
     * @param customerDto - Customer Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        if(customerRepository
                .existsByMobileNumber(customerDto.getMobileNumber())) {
            throw new CustomerExistException(
                    "There is customer with the same mobile number: "
                            + customerDto.getMobileNumber()
            );
        }
        Customer createdCustomer = customerRepository
                .save(CustomerMapper.mapToCustomer(customerDto));
        accountRepository.save(getNewAccount(createdCustomer));
    }


    /**
     * @param customer - Custer Object
     * @return new account details
     */
    private static Account getNewAccount(Customer customer) {
        Long randomAccNo = 1000000000L + new Random().nextInt(900000000);
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber(randomAccNo);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
    }
}
