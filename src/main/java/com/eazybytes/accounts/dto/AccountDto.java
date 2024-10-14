package com.eazybytes.accounts.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class AccountDto {
    @Pattern(regexp = "[1-9]{10}", message = "Account number is 10 digits required field")
    private Long accountNumber;
    @Pattern(regexp = "(savings|current|investment)", message = "Account types: savings,current or investment")
    private String accountType;
    @NotEmpty(message = "Branch address can not be null or empty")
    private String branchAddress;
}
