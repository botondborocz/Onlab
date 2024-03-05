package goalzone.repository;

import goalzone.model.AverageUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AverageUserRepository extends JpaRepository<AverageUser, Integer> {
    AverageUser findByUsername (String username);
}
