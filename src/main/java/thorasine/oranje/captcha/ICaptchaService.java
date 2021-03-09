package thorasine.oranje.captcha;

import thorasine.oranje.captcha.exception.ReCaptchaInvalidException;
import thorasine.oranje.captcha.exception.ReCaptchaUnavailableException;

public interface ICaptchaService {

    default void processResponse(final String response) throws ReCaptchaInvalidException,
            ReCaptchaUnavailableException {
    }

    default void processResponse(final String response, String action) throws ReCaptchaInvalidException,
            ReCaptchaUnavailableException {
    }

    String getReCaptchaSite();

    String getReCaptchaSecret();
}
