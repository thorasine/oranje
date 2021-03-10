package thorasine.oranje.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.stereotype.Component;
import thorasine.oranje.captcha.ICaptchaService;
import thorasine.oranje.captcha.exception.ReCaptchaUnavailableException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomBeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomBeforeAuthenticationFilter.class);

    @Autowired
    private ICaptchaService captchaService;

    @Autowired
    LoginAttemptService loginAttemptService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if(loginAttemptService.captchaRequired(request.getRemoteAddr())){
            String gReCaptchaResponse = request.getParameter("g-recaptcha-response");
            try {
                captchaService.processResponse(gReCaptchaResponse);
            } catch (ReCaptchaUnavailableException e) {
                // In case of Google downtime we just skip the captcha
                LOGGER.debug("Google API is unavailable: " + e.getMessage());
            }
        }
        return super.attemptAuthentication(request, response);
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Autowired
    @Override
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }

    @Autowired
    @Override
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }
}