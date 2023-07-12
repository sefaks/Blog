package com.example.spring.blogApp.payload;

import java.util.Date;

public class ErrorDetails {
    
    private Date timestamp;
    private String details;
    private String message;

    public ErrorDetails(Date timestamp, String details, String message) {
        this.timestamp = timestamp;
        this.details = details;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }
}
