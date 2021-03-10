package thorasine.oranje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import thorasine.oranje.persistence.model.Role;
import thorasine.oranje.persistence.model.User;
import thorasine.oranje.persistence.repository.RoleRepository;
import thorasine.oranje.persistence.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * This class is only for demonstrating purposes.
 *
 * @author Krisztian Zaja
 */
@Component
public class ManualSetup {

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    UserRepository userRepo;

    @PostConstruct
    private void init() {
        List<Role> roles = roleRepo.findAll();
        if (roles.size() == 0) {
            addRoles();
            addUsers();
        }
    }

    private void addRoles() {
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("EDITOR");
        Role role3 = new Role("USER");
        roleRepo.save(role1);
        roleRepo.save(role2);
        roleRepo.save(role3);
    }

    private void addUsers() {
        Role admin = roleRepo.findByName("ADMIN");
        Role editor = roleRepo.findByName("EDITOR");
        Role role_user = roleRepo.findByName("USER");
        User user0 = new User("Admin", new BCryptPasswordEncoder().encode("admin"), admin);
        User user1 = new User("User 1", new BCryptPasswordEncoder().encode("user1"), editor);
        user1.addRole(role_user);
        User user2 = new User("User 2", new BCryptPasswordEncoder().encode("user2"), editor);
        User user3 = new User("User 3", new BCryptPasswordEncoder().encode("user3"), role_user);
        userRepo.save(user0);
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
    }
}
