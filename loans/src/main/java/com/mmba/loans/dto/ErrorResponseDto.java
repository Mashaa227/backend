package com.mmba.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold the error response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "Api path invoked by client"
    )
    private String apiPath;

    @Schema(
            description = "error code representing the error happened"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;

    @Schema(
            description = "error timing that representing the error happened"
    )
    private LocalDateTime errorTime;

}
