package DaeguMogakko.BackEnd.redis;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, LocalDateTime> redisTemplate;
    private final long SESSION_TIME_OUT = 4320L;

    public void insertRefreshToken(String refreshToken) {
        redisTemplate.opsForValue().set(refreshToken, LocalDateTime.now(), SESSION_TIME_OUT);
    }

    public boolean isExistRefreshToken(String refreshToken) {
        return redisTemplate.hasKey(refreshToken);
    }

}
