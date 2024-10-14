package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class CustomerDto {

    @Pattern(regexp = "[A-Za-z]{3,} ?[a-zA-Z]{3,} ?[a-zA-z]*",
            message = "Requires minimum of 2 names with at least 3 alphabet characters each")
    private String name;

    @Email(message = "Not a valid email pattern")
    private String email;

    @Pattern(regexp = "0[7-9][01][0-9]{8}", message = "Not a valid Nigeria mobile number")
    private String mobileNumber;

    private AccountDto accountDto;
}

