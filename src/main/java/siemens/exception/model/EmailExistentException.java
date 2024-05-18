package siemens.exception.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class EmailExistentException extends CustomException{
    private static final String MESSAGE = "This email already exists!";
    private static final HttpStatus httpStatus = HttpStatus.CONFLICT;
    public EmailExistentException() {
        super(MESSAGE,httpStatus, new ArrayList<>());
    }
}
