package com.library.exception;

import jakarta.persistence.EntityNotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;
import org.webjars.NotFoundException;

@RestControllerAdvice(annotations = MyExceptionHandler.class)
public final class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
     @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> badRequestException(final EntityNotFoundException ex)
    {
        Response response = new Response();
        response.setMessage(ex.getLocalizedMessage());
        response.setDescription("There is no such object");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response>  badRequestException(final MethodArgumentNotValidException ex)
    {
        Response response = new Response();
        response.setMessage(ex.getLocalizedMessage());
        response.setDescription("Incorrect request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> badRequestException(final HttpMessageNotReadableException ex)
    {
        Response response = new Response();
        response.setMessage(ex.getLocalizedMessage());
        response.setDescription("The request body was passed incorrectly");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleMissingServletRequestParametersException(final MissingServletRequestParameterException ex)
    {
        Response response = new Response();
        response.setMessage(ex.getLocalizedMessage());
        response.setDescription("Missing parameters");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<Response> handleHttpsRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex)
    {
        Response response = new Response();
        response.setMessage(ex.getLocalizedMessage());
        response.setDescription("Not supported method");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }
    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleMissingPathVariable(MissingPathVariableException ex)
    {
        Response response = new Response();
        response.setMessage(ex.getLocalizedMessage());
        response.setDescription("Required path variable are missing");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
    {
        Response response = new Response();
        response.setMessage(ex.getLocalizedMessage());
        response.setDescription("Invalid arguments");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleInternalServerError(Exception ex)
    {
        Response response= new Response();
        response.setMessage(ex.getLocalizedMessage());
        response.setDescription("Unknown error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleNotFound(NotFoundException ex)
    {
        Response response = new Response();
        response.setMessage(ex.getLocalizedMessage());
        response.setDescription("The request resource was not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<Response> handleMethodNotAllowed(MethodNotAllowedException ex)
    {
        Response response = new Response();
        response.setMessage(ex.getLocalizedMessage());
        response.setDescription("The http method is not supported");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }
}
