package thorasine.oranje.captcha.exception;

import org.springframework.security.core.AuthenticationException;

public final class ReCaptchaUnavailableException extends AuthenticationException {

    public ReCaptchaUnavailableException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ReCaptchaUnavailableException(final String message) {
        super(message);
    }

}
