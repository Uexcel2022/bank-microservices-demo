package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
@Schema(
        name = "Contacts",
        description = "Schema to hold contact information"
)
@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDto(
        String message, Map<String,String> contactDetails, List<String> onCallSupport) {
}
