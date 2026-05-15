package com.pht.certify.exception;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.mongodb.MongoTimeoutException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MongoTimeoutException.class)
    public ResponseEntity<String> handleMongoTimeout(MongoTimeoutException ex) {
        return ResponseEntity
                .status(HttpStatus.GATEWAY_TIMEOUT)
                .body("Database connection timeout.");
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccess(DataAccessException ex) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Database error occurred.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong.");
    }
}