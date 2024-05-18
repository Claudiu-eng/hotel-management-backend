package siemens.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import siemens.enums.RoomType;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private Integer roomNumber;
    private Float price;
    private Boolean isAvailable;
    private RoomType type;

    @ManyToOne
    @JoinColumn(name="hotel", nullable=false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Booking> bookings;


}
