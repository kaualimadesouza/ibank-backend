package com.kaualimadesouza.bank.Exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException () { super("This User Already Exists"); };
    public UserAlreadyExistsException (String message) {
        super(message);
    }
}
