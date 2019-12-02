package com.byteworks.fooddelivery.dto;

public class ResponseDto {
    private String message;
    private Integer status;

    public ResponseDto(Integer status, String message) {
        this.message = message;
        this.status = status;
    }

    public ResponseDto() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
