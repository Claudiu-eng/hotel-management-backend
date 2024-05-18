package siemens.model.dto;


import lombok.*;
import siemens.enums.RoomType;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class RoomDTO {

    @NonNull
    private Integer roomNumber;
    @NonNull
    private RoomType type;
    @NonNull
    private Float price;
    @NonNull
    private Boolean isAvailable;

}
