package repository;

import model.AverageUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AverageUserRepository extends JpaRepository<AverageUser, Integer> {
}
