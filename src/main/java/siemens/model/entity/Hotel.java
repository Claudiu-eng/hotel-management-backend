package siemens.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel")
public class Hotel {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    private String name;
    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Feedback> feedbackList;


}
