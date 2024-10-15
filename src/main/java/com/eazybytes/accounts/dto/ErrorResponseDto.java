package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Setter @Getter @AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {
    @Schema(
            description = "Api path invoked when the error occurred"
    )
    private String apiPath;
    @Schema(
            description = "Holds the status code in the response"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Holds the status message in the response"
    )
    private String errorMessage;
    @Schema(
            description = "Holds the time the error occurred in the response"
    )
    private LocalDateTime errorTime;
}
