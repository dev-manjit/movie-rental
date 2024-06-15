package com.etraveli.movierental.exception;

import lombok.Getter;

@Getter
public class RentalException extends RuntimeException {
    private RentalError error;
    public RentalException(RentalError error) {
        super(error.getMessage());
        this.error=error;
    }

    public RentalException(RentalError error, Throwable cause) {
        super(error.getMessage(), cause);
        this.error=error;
    }

}
