package com.mmba.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/* this will help the developer and client to understand the error and to debug and log */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Error",
        description = "schema that holds the error information"
)
public class ErrorResponseDto {

    @Schema(
            description = "Api path that invoked by client"
    )
    private String apiPath;

    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;

    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDateTime errorTime;;
}
