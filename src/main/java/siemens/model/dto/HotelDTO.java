package siemens.model.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class HotelDTO {

    @NonNull
    private Long id;
    @NonNull
    private String name;
    private List<RoomDTO> rooms;

}
