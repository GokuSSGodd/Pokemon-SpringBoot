package org.example.jpademo.response;

import org.springframework.http.HttpStatusCode;

/**
 * A record (immutable) that stores the HTTP response status code
 * and the message it prints out when it is called
 * ex - Not Found, Pokemon with name Squirtle not found
 **/
public record ApiError (
    HttpStatusCode httpStatusCode,
    String message
){}
