package thorasine.oranje.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import thorasine.oranje.persistence.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(@Param("username") String username);
}
