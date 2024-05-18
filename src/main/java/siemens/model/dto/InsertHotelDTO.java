package siemens.model.dto;


import lombok.*;

import java.io.DataOutputStream;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class InsertHotelDTO {

    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Double latitude;
    @NonNull
    private Double longitude;
    private List<InsertRoomDTO> rooms;

}
