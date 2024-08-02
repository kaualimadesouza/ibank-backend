package com.kaualimadesouza.bank.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException () { super("Not Found."); };
    public UserNotFoundException (String message) {
        super(message);
    }
}
