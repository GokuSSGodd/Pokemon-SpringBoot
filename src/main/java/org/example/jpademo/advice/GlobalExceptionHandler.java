package org.example.jpademo.advice;

import org.example.jpademo.exception.PokemonNotFoundException;
import org.example.jpademo.exception.PokemonRegionNotFoundException;
import org.example.jpademo.response.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code GlobalExceptionHandler} class provides centralized exception handling
 * across all controllers in the application using Spring’s {@link org.springframework.web.bind.annotation.RestControllerAdvice}.
 * It ensures that consistent and meaningful HTTP responses are returned when exceptions occur,
 * improving error transparency and API usability.
 *
 * <p>This class intercepts specific exceptions such as validation errors and
 * custom exceptions like {@code PokemonNotFoundException} and {@code PokemonRegionNotFoundException},
 * converting them into well-structured HTTP responses with appropriate status codes and messages.</p>
 *
 * <h3>Annotations and Concepts Explained:</h3>
 *
 * <ul>
 *   <li><b>@RestControllerAdvice</b> – A specialized form of {@code @ControllerAdvice} that automatically applies
 *       {@code @ResponseBody} semantics. It enables global exception handling for all REST controllers
 *       without the need to duplicate code in each controller.</li>
 *
 *   <li><b>@ExceptionHandler</b> – Marks methods that handle specific exception types.
 *       When the specified exception is thrown from any controller, the corresponding
 *       method in this class is invoked to build a custom HTTP response.</li>
 *
 *   <li><b>MethodArgumentNotValidException</b> – Thrown when validation on a request body or parameter fails.
 *       The handler extracts validation errors and returns them as a map of field names and error messages
 *       with a {@code 400 Bad Request} response.</li>
 *
 *   <li><b>PokemonRegionNotFoundException</b> and <b>PokemonNotFoundException</b> –
 *       Custom domain-specific exceptions indicating that a Pokémon or its region was not found.
 *       These handlers return a structured {@link org.example.jpademo.response.ApiError} object
 *       with an HTTP {@code 404 Not Found} status.</li>
 *
 *   <li><b>ResponseEntity</b> – Used to build HTTP responses programmatically, allowing full control
 *       over status codes, headers, and body content.</li>
 *
 *   <li><b>ApiError</b> – A custom response model encapsulating error details such as
 *       the HTTP status and a human-readable error message, promoting consistent API responses.</li>
 * </ul>
 *
 * <h3>Overall Behavior:</h3>
 * <p>
 * Whenever an exception occurs in any controller, this class ensures that the API responds
 * with a meaningful error message rather than an unhandled stack trace.
 * Validation errors result in a {@code 400 Bad Request}, while missing resources
 * like Pokémon or regions trigger a {@code 404 Not Found} response containing an {@code ApiError} object.
 * </p>
 *
 * <p>This design promotes cleaner controller code, consistent error formatting,
 * and better client-side error handling in RESTful APIs.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PokemonRegionNotFoundException.class)
    public ResponseEntity<Object> handlePokemonRegionNotFound(PokemonRegionNotFoundException ex) {
        var body = new ApiError(HttpStatus.NOT_FOUND,  ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<Object> handlePokemonException(PokemonNotFoundException ex) {
        var body = new ApiError(HttpStatus.NOT_FOUND,  ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}