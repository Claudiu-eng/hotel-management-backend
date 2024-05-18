package siemens.model.mapper;

import org.springframework.stereotype.Component;
import siemens.model.dto.InsertRoomDTO;
import siemens.model.dto.RegisterUserDTO;
import siemens.model.entity.Room;
import siemens.model.entity.User;

import java.util.List;

@Component
public class UserMapper {

    public User dtoToEntity(RegisterUserDTO registerUserDTO) {

        return User.builder()
                .name(registerUserDTO.getName())
                .email(registerUserDTO.getEmail())
                .password(registerUserDTO.getPassword())
                .build();
    }

}
