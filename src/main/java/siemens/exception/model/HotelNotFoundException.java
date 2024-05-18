package siemens.exception.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class HotelNotFoundException extends CustomException{
    private static final String MESSAGE = "Hotel not found!";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    public HotelNotFoundException() {
        super(MESSAGE,httpStatus, new ArrayList<>());
    }
}
