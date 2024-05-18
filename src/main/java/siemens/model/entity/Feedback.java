package siemens.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import siemens.enums.RoomType;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String comment;
    private Integer rating;
    private Long date;

    @ManyToOne
    @JoinColumn(name="hotel", nullable=false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name="user", nullable=false)
    private User user;


}
