package thorasine.oranje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import thorasine.oranje.model.User;
import thorasine.oranje.repository.UserRepository;

import java.util.Date;

@Service
public class UserLoginSuccessService implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String userName = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(userName);
        user.setLastLogin(new Date());
        userRepository.save(user);
    }
}