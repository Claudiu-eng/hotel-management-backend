package siemens.exception.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class BookingNotFoundException extends CustomException{
    private static final String MESSAGE = "Booking not found!";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    public BookingNotFoundException() {
        super(MESSAGE,httpStatus, new ArrayList<>());
    }
}
