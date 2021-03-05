package thorasine.oranje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import thorasine.oranje.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(@Param("username") String username);
}
