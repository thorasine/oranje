package thorasine.oranje.captcha;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import thorasine.oranje.captcha.exception.ReCaptchaInvalidException;
import thorasine.oranje.captcha.exception.ReCaptchaUnavailableException;

import java.net.URI;

@Service("captchaService")
public class CaptchaService extends AbstractCaptchaService {

    @Override
    public void processResponse(final String response) {
        securityCheck(response);

        final URI verifyUri = URI.create(String.format(RECAPTCHA_URL_TEMPLATE, getReCaptchaSecret(), response,
                getClientIP()));
        try {
            final GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);
            if (!googleResponse.isSuccess()) {
                if (googleResponse.hasClientError()) {
                    reCaptchaAttemptService.reCaptchaFailed(getClientIP());
                }
                throw new ReCaptchaInvalidException("reCaptcha was not successfully validated.");
            }
        } catch (RestClientException rce) {
            throw new ReCaptchaUnavailableException("Registration unavailable at this time. Please try again later."
                    , rce);
        }
        reCaptchaAttemptService.reCaptchaSucceeded(getClientIP());
    }
}