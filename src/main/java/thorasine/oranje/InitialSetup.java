package thorasine.oranje;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import thorasine.oranje.persistence.model.Role;
import thorasine.oranje.persistence.model.User;
import thorasine.oranje.persistence.repository.RoleRepository;
import thorasine.oranje.persistence.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * This class is only for demonstrating purposes.
 *
 * @author Krisztian Zaja
 */
@Component
public class InitialSetup {

    @Resource
    RoleRepository roleRepo;

    @Resource
    UserRepository userRepo;

    @PostConstruct
    private void init() {
        long nRoles = roleRepo.count();
        if (nRoles == 0) {
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

    public void addUsers(){
        Role admin = roleRepo.findByName("ADMIN");
        Role editor = roleRepo.findByName("EDITOR");
        Role role_user = roleRepo.findByName("USER");
        User user0 = new User("Admin", new BCryptPasswordEncoder().encode("admin"));
        User user1 = new User("User 1", new BCryptPasswordEncoder().encode("user1"));
        User user2 = new User("User 2", new BCryptPasswordEncoder().encode("user2"));
        User user3 = new User("User 3", new BCryptPasswordEncoder().encode("user3"));
        user0.addRole(admin);
        user1.addRole(editor);
        user1.addRole(role_user);
        user2.addRole(editor);
        user3.addRole(role_user);
        userRepo.save(user0);
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
    }
}
