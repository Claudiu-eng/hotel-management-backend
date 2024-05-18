package siemens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siemens.model.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
