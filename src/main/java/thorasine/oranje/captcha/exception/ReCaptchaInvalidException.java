package thorasine.oranje.captcha.exception;

import org.springframework.security.core.AuthenticationException;

public final class ReCaptchaInvalidException extends AuthenticationException {

    public ReCaptchaInvalidException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ReCaptchaInvalidException(final String message) {
        super(message);
    }

}
