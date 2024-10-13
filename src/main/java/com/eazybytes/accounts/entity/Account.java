package com.eazybytes.accounts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Account extends BaseEntity {
    @Id
    private Long accountNumber;
    private String accountType;
    private String branchAddress;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Customer customer;
}
