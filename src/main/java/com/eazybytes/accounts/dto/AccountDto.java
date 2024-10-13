package com.eazybytes.accounts.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class AccountDto {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
