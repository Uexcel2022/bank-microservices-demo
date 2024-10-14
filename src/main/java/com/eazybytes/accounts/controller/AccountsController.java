package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api" , produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

    private final IAccountService iAccountService;

    @PostMapping(path = "/create-account")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch-customer-m-num")
    public ResponseEntity<CustomerDto> updateAccount(@RequestParam("m-num") String mobileNumber){
        return ResponseEntity.ok().body(iAccountService.getCustomerByMobileNumber(mobileNumber));
    }

}
