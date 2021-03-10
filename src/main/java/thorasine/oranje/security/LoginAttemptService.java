package thorasine.oranje.security;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    @Autowired
    LoginSettings loginSettings;

    private int MAX_ATTEMPTS;
    private int EXPIRE_TIME;
    private LoadingCache<String, Integer> attemptsCache;

    @PostConstruct
    private void init() {
        MAX_ATTEMPTS = loginSettings.getMaxAttemptsBeforeCaptcha();
        EXPIRE_TIME = loginSettings.getHoursToDropCaptcha();
        attemptsCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(EXPIRE_TIME, TimeUnit.HOURS)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(String key) {
        int attempts = attemptsCache.getUnchecked(key);
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean captchaRequired(final String key) {
        return attemptsCache.getUnchecked(key) >= MAX_ATTEMPTS;
    }
}
