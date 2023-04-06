package com.backend.university.common.error.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class APIErrorResponse {

    private String message;
    private int code;
    private String status;
    private LocalDate timestamp;
    private String description;

}
