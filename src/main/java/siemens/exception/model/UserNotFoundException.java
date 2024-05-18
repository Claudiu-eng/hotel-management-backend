package siemens.exception.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class UserNotFoundException extends CustomException{
    private static final String MESSAGE = "User not found!";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    public UserNotFoundException() {
        super(MESSAGE,httpStatus, new ArrayList<>());
    }
}
