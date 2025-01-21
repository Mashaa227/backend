package com.mmba.accounts.dto;

public class ResponseDto {

    private String statusCode;
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
