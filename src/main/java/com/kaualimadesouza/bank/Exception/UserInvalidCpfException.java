package com.kaualimadesouza.bank.Exception;

public class UserInvalidCpfException extends RuntimeException{
    public UserInvalidCpfException () { super("Invalidated CPF."); };
    public UserInvalidCpfException (String message) {
        super(message);
    }
}
