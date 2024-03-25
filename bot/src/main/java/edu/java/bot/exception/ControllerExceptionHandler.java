package edu.java.bot.exception;

import edu.java.bot.generated.model.ApiErrorResponse;
import java.util.Arrays;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackages = {"edu.java.bot.controller"})
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handle(Exception e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(new ApiErrorResponse("Exception", "Service unavailable", e.getClass().getName(), e.getMessage(),
                Arrays.stream(ExceptionUtils.getStackFrames(e)).toList()
            ));
    }
}
