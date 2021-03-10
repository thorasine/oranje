package thorasine.oranje.captcha;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service("reCaptchaAttemptService")
public class ReCaptchaAttemptService {

    @Autowired
    private CaptchaSettings captchaSettings;

    private int MAX_ATTEMPTS;
    private int EXPIRE_TIME;
    private LoadingCache<String, Integer> attemptsCache;

    @PostConstruct
    private void init() {
        MAX_ATTEMPTS = captchaSettings.getMaxFailuresBeforeBlock();
        EXPIRE_TIME = captchaSettings.getHoursToDropBlock();
        attemptsCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(EXPIRE_TIME, TimeUnit.HOURS)
                .build(new CacheLoader<>() {
                    @Override
                    public Integer load(final String key) {
                        return 0;
                    }
                });
    }

    public void reCaptchaSucceeded(final String key) {
        attemptsCache.invalidate(key);
    }

    public void reCaptchaFailed(final String key) {
        int attempts = attemptsCache.getUnchecked(key);
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(final String key) {
        return attemptsCache.getUnchecked(key) >= MAX_ATTEMPTS;
    }
}
