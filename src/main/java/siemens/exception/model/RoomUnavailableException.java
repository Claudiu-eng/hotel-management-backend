package siemens.exception.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class RoomUnavailableException extends CustomException{
    private static final String MESSAGE = "Room unavailable!";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    public RoomUnavailableException() {
        super(MESSAGE,httpStatus, new ArrayList<>());
    }
}
