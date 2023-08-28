package com.apispringboot.blog.exception;

import com.apispringboot.blog.Dto.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundExceptions.class)
    public ResponseEntity<ErrorDetails> handlerResourceNotFoundException(ResourceNotFoundExceptions exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<ErrorDetails> handlerBlogAppException(BlogAppException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handlerGlobalExceptionHandler(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nameColumn = ((FieldError)error).getField();
            String message = error.getDefaultMessage();

            errores.put(nameColumn, message);
        });

        return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
    }
}
