package com.example.demo.exception;

public class VolunteerNotFoundException extends RuntimeException{
    public VolunteerNotFoundException(String message){
        super(message);
    }
}
