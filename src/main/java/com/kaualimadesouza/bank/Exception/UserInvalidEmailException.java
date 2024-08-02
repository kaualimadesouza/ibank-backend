package com.kaualimadesouza.bank.Exception;

public class UserInvalidEmailException extends RuntimeException {
    public UserInvalidEmailException () { super("Invalidated Email."); };
    public UserInvalidEmailException (String message) {
        super(message);
    }
}
