package siemens.model.dto;


import lombok.*;
import siemens.enums.RoomType;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

    @NonNull
    private Integer roomNumber;
    @NonNull
    private Long hotelId;
    @NonNull
    private String email;
    private String time;

}
