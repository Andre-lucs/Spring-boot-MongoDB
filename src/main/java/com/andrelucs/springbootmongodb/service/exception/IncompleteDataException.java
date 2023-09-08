package com.andrelucs.springbootmongodb.service.exception;

public class IncompleteDataException extends RuntimeException{
    public IncompleteDataException(String msg){
        super(msg);
    }
}
