package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountsController {

    private final IAccountService iAccountService;

    @PostMapping(path = "/create-account")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){

        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch-customer-m-num")
    public ResponseEntity<CustomerDto> updateAccount(@Valid @RequestParam("m-num")
                                                         @Pattern(regexp = "0[7-9][01][0-9]{8}", message = "Not a valid Nigeria mobile number")
                                                         String mobileNumber
    ){

        return ResponseEntity.ok().body(iAccountService.getCustomerByMobileNumber(mobileNumber));
    }

    @PutMapping("/update-account")
    public  ResponseEntity<ResponseDto> updateBankAccount(@Valid @RequestBody CustomerDto customerDto) {

        boolean isUpdated = iAccountService.updateCustomer(customerDto);

        if (isUpdated) {
            return ResponseEntity.ok()
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam("accountNumber")
                                                         @Pattern(regexp = "[0-9]{10}", message = "Account number must be 10 digits")
                                                         String accountNumber
    ){
            Boolean isDeleted = iAccountService.deleteCustomer(Long.parseLong(accountNumber));
        if (isDeleted) {
            return ResponseEntity.ok()
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }

    }

}
