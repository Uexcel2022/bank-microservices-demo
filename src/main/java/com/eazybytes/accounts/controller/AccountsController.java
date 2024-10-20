package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs FOR EazyBank",
        description = "CRUD REST APIs in EazyBank to CREAT, RETRIEVE, UPDATE AND DELETE Account details"
)
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountsController {

    private final IAccountService iAccountService;

    @Operation(
            summary = "REST API To Create Account ",
            description = "REST API to create new Customer and Account details inside EazyBank",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "HTTP Status CREATED"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )

    @PostMapping(path = "/create-account")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){

        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Details REST API",
            description = "REST API to fetch Customer and Account details  inside EazyBank",
            responses = {
                    @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status Ok"
                ),
                    @ApiResponse(
                        responseCode = "500",
                        description = "HTTP status Internal Server Error",
                        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)
                        )
                )
        }
    )

    @GetMapping("/fetch-customer")
    public ResponseEntity<CustomerDto> updateAccount(@Valid @RequestParam("mobileNumber")
                                                         @Pattern(regexp = "0[7-9][01][0-9]{8}",
                                                                 message = "Not a valid Nigeria mobile number")
                                                         String mobileNumber
    ){

        return ResponseEntity.ok().body(iAccountService.getCustomerByMobileNumber(mobileNumber));
    }

    @Operation(
            summary = "Update Details REST API",
            description = "REST API to update Customer and Account details  inside EazyBank",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status Ok"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP status Internal Server Error",

                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "417",
                            description = "Exception Failed"
                    )
            }

    )
    @PutMapping("/update-account")
    public  ResponseEntity<ResponseDto> updateBankAccount(@Valid @RequestBody CustomerDto customerDto) {

        boolean isUpdated = iAccountService.updateCustomer(customerDto);

        if (isUpdated) {
            return ResponseEntity.ok()
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_Update));
        }
    }
    @Operation(
            summary = "Delete Details REST API",
            description = "REST API to delete Customer and Account details  inside EazyBank",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status Ok"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP status Internal Server Error",
                            content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                      )
                    ),
                    @ApiResponse(
                            responseCode = "417",
                            description = "Exception Failed"
                    )
            }

    )
    @DeleteMapping("/delete-account")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam("mobileNumber")
                                                         @Pattern(regexp = "^0[7-9][01][0-9]{8}$", message = "Invalid mobile number")
                                                         String mobileNumber
    ){
            Boolean isDeleted = iAccountService.deleteCustomer(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.ok()
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_Delete));
        }

    }

}
