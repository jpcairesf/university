package com.backend.university.common.error.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class APIErrorResponse {

    private String message;
    private int code;
    private String status;
    private Date timestamp;
    private String description;

}
