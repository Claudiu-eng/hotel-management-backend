package siemens.model.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class TokenDTO {

    @NonNull
    private String token;


}
