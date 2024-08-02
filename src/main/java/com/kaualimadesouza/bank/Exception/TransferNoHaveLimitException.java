package com.kaualimadesouza.bank.Exception;

public class TransferNoHaveLimitException extends RuntimeException{
    public TransferNoHaveLimitException () { super("You don't have money."); };
    public TransferNoHaveLimitException (String message) {
        super(message);
    }
}
