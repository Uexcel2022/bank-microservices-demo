package com.eazybytes.accounts.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    private LocalDateTime createdAT;
    private String createdBy;
    private LocalDateTime updatedAT;
    private String updatedBy;
}
