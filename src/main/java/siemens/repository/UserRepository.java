package siemens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siemens.model.entity.Hotel;
import siemens.model.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
