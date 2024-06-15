package com.etraveli.movierental.exception;

public class IllegalOperationException extends RentalException{

    public IllegalOperationException(RentalError error) {
        super(error);
    }

    public IllegalOperationException(RentalError error, Throwable cause) {
        super(error, cause);
    }
}
