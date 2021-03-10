package thorasine.oranje.captcha.exception;

import org.springframework.security.core.AuthenticationException;

public final class ReCaptchaBlockedException extends AuthenticationException {

    public ReCaptchaBlockedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ReCaptchaBlockedException(final String message) {
        super(message);
    }

}
