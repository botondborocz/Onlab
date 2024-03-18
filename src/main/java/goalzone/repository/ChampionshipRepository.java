package goalzone.repository;

import goalzone.model.AverageUser;
import goalzone.model.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipRepository extends JpaRepository<Championship, Integer> {
    Championship findByName (String name);
}
