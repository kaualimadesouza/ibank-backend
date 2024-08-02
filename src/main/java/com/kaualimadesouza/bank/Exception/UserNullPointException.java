package com.kaualimadesouza.bank.Exception;

public class UserNullPointException extends RuntimeException{

    public UserNullPointException () { super("Insert all data."); };
    public UserNullPointException (String message) {
        super(message);
    }
}
