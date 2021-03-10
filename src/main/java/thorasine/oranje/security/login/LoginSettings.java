package thorasine.oranje.security.login;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom.login")
public class LoginSettings {

    private int maxAttemptsBeforeCaptcha;
    private int hoursToDropCaptcha;

    public void setMaxAttemptsBeforeCaptcha(int maxAttemptsBeforeCaptcha) {
        this.maxAttemptsBeforeCaptcha = maxAttemptsBeforeCaptcha;
    }

    public void setHoursToDropCaptcha(int hoursToDropCaptcha) {
        this.hoursToDropCaptcha = hoursToDropCaptcha;
    }

    public int getMaxAttemptsBeforeCaptcha() {
        return maxAttemptsBeforeCaptcha;
    }

    public int getHoursToDropCaptcha() {
        return hoursToDropCaptcha;
    }
}
