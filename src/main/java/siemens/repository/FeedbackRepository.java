package siemens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siemens.model.entity.Feedback;
import siemens.model.entity.Room;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
}
