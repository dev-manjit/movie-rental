package com.etraveli.movierental.exception;

public class RecordNotFoundException extends RentalException{

    public RecordNotFoundException(RentalError error) {
        super(error);
    }

    public RecordNotFoundException(RentalError error, Throwable cause) {
        super(error, cause);
    }
}
