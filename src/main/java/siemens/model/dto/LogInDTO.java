package siemens.model.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class LogInDTO {

    @NonNull
    private String email;
    @NonNull
    private String password;


}
