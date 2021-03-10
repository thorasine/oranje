package thorasine.oranje.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import thorasine.oranje.captcha.exception.ReCaptchaBlockedException;
import thorasine.oranje.captcha.exception.ReCaptchaInvalidException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof ReCaptchaInvalidException) {
            getRedirectStrategy().sendRedirect(request, response, "/login?error=2");
        } else if (exception instanceof ReCaptchaBlockedException){
            getRedirectStrategy().sendRedirect(request, response, "/login?error=3");
        } else {
            getRedirectStrategy().sendRedirect(request, response, "/login?error=1");
        }
    }

}
