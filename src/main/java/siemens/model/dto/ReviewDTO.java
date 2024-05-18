package siemens.model.dto;


import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    @NonNull
    private Integer roomNumber;
    @NonNull
    private Long hotelId;
    @NonNull
    private String email;
    private String review;

}
