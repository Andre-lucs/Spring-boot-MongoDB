package com.andrelucs.springbootmongodb.resource.exception;

import com.andrelucs.springbootmongodb.service.exception.IncompleteDataException;
import com.andrelucs.springbootmongodb.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        var status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(),"Not Found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(IncompleteDataException.class)
    public ResponseEntity<StandardError> incompleteData(IncompleteDataException e, HttpServletRequest request){
        var status = HttpStatus.BAD_REQUEST;
        var err = new StandardError(System.currentTimeMillis(), status.value(), "Missing required data", e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
