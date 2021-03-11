package thorasine.oranje.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import thorasine.oranje.security.login.CustomBeforeAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public CustomBeforeAuthenticationFilter customBeforeAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/css/**", "/img/**", "/js/**", "/webjars/**").permitAll()
                .antMatchers("/").authenticated()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/user").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/editor").hasAnyAuthority("EDITOR", "ADMIN")
                .antMatchers("/admin").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(customBeforeAuthenticationFilter, CustomBeforeAuthenticationFilter.class)
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/denied");
    }
}
