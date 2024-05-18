package siemens.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import siemens.exception.model.EmailExistentException;
import siemens.exception.model.UserNotFoundException;
import siemens.model.dto.LogInDTO;
import siemens.model.dto.RegisterUserDTO;
import siemens.model.dto.TokenDTO;
import siemens.model.entity.User;
import siemens.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public TokenDTO register(RegisterUserDTO request) throws EmailExistentException {
        if(userRepository.findById(request.getEmail()).isPresent())
            throw new EmailExistentException();
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build();
        userRepository.save(user);
        user = userRepository.findById(request.getEmail()).orElseThrow(EmailExistentException::new);
        var jwtToken = jwtService.generateToken(user);
        return TokenDTO.builder()
                .token(jwtToken).build();
    }

    public TokenDTO authenticate(LogInDTO request) throws UserNotFoundException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findById(request.getEmail()).orElseThrow(UserNotFoundException::new);
        var jwtToken = jwtService.generateToken(user);
        return TokenDTO.builder()
                .token(jwtToken).build();
    }

}
