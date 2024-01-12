package com.example.myblog.myblog.Dto;

import java.sql.Time;
import java.util.Date;

public class ErrorDetails {

    private Date date;
    private String message;
    private String description;

    public ErrorDetails(Date date, String message, String description) {
        this.date=date;
        this.message=message;
        this.description=description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descriptionon) {
        this.description = descriptionon;
    }
}
