package siemens.exception.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class BookingExpiredException extends CustomException{
    private static final String MESSAGE = "Booking Expired!";
    private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public BookingExpiredException() {
        super(MESSAGE,httpStatus, new ArrayList<>());
    }
}
