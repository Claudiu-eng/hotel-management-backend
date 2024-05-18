package siemens.model.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class GetHotelsDTO {

    @NonNull
    private Float distanceInKm;
    @NonNull
    private Double longitude;
    @NonNull
    private Double latitude;
    @NonNull
    private String email;

}
