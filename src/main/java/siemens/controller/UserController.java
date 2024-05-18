package siemens.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siemens.exception.model.EmailExistentException;
import siemens.exception.model.UserNotFoundException;
import siemens.model.dto.LogInDTO;
import siemens.model.dto.RegisterUserDTO;
import siemens.model.dto.TokenDTO;
import siemens.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody RegisterUserDTO user) throws EmailExistentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LogInDTO user) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.authenticate(user));
    }


}
