package thorasine.oranje.captcha;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha")
public class CaptchaSettings {

    private String site;
    private String secret;
    private int maxFailuresBeforeBlock;
    private int hoursToDropBlock;

    public void setSite(String site) {
        this.site = site;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setMaxFailuresBeforeBlock(int maxFailuresBeforeBlock) {
        this.maxFailuresBeforeBlock = maxFailuresBeforeBlock;
    }

    public void setHoursToDropBlock(int hoursToDropBlock) {
        this.hoursToDropBlock = hoursToDropBlock;
    }

    public String getSite() {
        return site;
    }

    public String getSecret() {
        return secret;
    }

    public int getMaxFailuresBeforeBlock() {
        return maxFailuresBeforeBlock;
    }

    public int getHoursToDropBlock() {
        return hoursToDropBlock;
    }
}
