package thorasine.oranje.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import thorasine.oranje.persistence.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(@Param("name") String name);
}
