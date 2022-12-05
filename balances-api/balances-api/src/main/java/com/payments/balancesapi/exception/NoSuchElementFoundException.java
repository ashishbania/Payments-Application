package com.payments.balancesapi.exception;
public class NoSuchElementFoundException
        extends RuntimeException {

    private String message;

    public NoSuchElementFoundException() {}

    public NoSuchElementFoundException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
