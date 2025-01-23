package com.mmba.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Response",
        description = "Schema to hold the response information"
)
@SuppressWarnings("unused")
public class ResponseDto {

    @Schema(
            description = "Status code in the response",
            example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Status information the response",
            example = "request processed successfully"
    )
    private String statusMessage;

    public ResponseDto() {
        // Initialization if necessary
    }

    // Constructor that accepts two String parameters
    public ResponseDto(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    // Getters and setters (optional)
    public String getMessage() {
        return statusCode;
    }

    public void setMessage(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return statusMessage;
    }

    public void setStatus(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
