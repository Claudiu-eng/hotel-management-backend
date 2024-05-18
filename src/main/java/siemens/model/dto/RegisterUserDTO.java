package siemens.model.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class RegisterUserDTO {

    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String name;


}
