package siemens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siemens.model.entity.Room;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
}
