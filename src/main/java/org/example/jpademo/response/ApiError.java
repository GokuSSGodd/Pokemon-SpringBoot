package org.example.jpademo.response;

import org.springframework.http.HttpStatusCode;

public record ApiError (
    HttpStatusCode httpStatusCode,
    String message
){}
