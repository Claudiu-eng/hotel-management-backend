package siemens.exception.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class RoomNotFoundException extends CustomException{
    private static final String MESSAGE = "Room not found!";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    public RoomNotFoundException() {
        super(MESSAGE,httpStatus, new ArrayList<>());
    }
}
