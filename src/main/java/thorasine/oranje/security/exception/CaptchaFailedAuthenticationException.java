package thorasine.oranje.security.exception;

import org.springframework.security.core.AuthenticationException;

public class CaptchaFailedAuthenticationException extends AuthenticationException {

    public CaptchaFailedAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CaptchaFailedAuthenticationException(String msg) {
        super(msg);
    }
}
