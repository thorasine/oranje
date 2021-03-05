package thorasine.oranje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import thorasine.oranje.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(@Param("name") String name);
}
