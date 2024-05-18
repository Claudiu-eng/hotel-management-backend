package siemens.model.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class GetHotelOnMaxDistance {

    @NonNull
    private Double userLatitude;
    @NonNull
    private Double userLongitude;
    @NonNull
    private Double maxDistanceInKm;


}
