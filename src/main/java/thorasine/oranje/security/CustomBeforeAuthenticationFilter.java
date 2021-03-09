package thorasine.oranje.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.stereotype.Component;
import thorasine.oranje.captcha.ICaptchaService;
import thorasine.oranje.captcha.exception.ReCaptchaInvalidException;
import thorasine.oranje.captcha.exception.ReCaptchaUnavailableException;
import thorasine.oranje.security.exception.CaptchaFailedAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomBeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private ICaptchaService captchaService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = request.getParameter("username");
        String gReCaptchaResponse = request.getParameter("g-recaptcha-response");
        try {
            captchaService.processResponse(gReCaptchaResponse);
        } catch (ReCaptchaInvalidException e) {
            throw new CaptchaFailedAuthenticationException("invalid captcha");
        } catch (ReCaptchaUnavailableException e) {
            // In case of Google downtime just skip the captcha
            // System.out.println("Google API is down: " + e.getMessage());
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