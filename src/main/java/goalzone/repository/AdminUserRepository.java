package goalzone.repository;

import goalzone.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {
    AdminUser findByUsername (String username);
}
