package thorasine.oranje.captcha;

import thorasine.oranje.captcha.exception.ReCaptchaBlockedException;
import thorasine.oranje.captcha.exception.ReCaptchaInvalidException;
import thorasine.oranje.captcha.exception.ReCaptchaUnavailableException;

public interface ICaptchaService {

    default void processResponse(final String response) throws ReCaptchaInvalidException,
            ReCaptchaUnavailableException, ReCaptchaBlockedException {
    }

    String getReCaptchaSite();

    String getReCaptchaSecret();
}
