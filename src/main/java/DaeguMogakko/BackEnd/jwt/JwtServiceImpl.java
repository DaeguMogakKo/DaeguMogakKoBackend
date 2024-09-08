package DaeguMogakko.BackEnd.jwt;

import DaeguMogakko.BackEnd.exception.InvalidTokenException;
import DaeguMogakko.BackEnd.exception.MemberNotFoundException;
import DaeguMogakko.BackEnd.member.Member;
import DaeguMogakko.BackEnd.member.MemberRepository;
import DaeguMogakko.BackEnd.redis.RedisService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{
    private final JwtUtil jwtUtil;
    private final RedisService redisService;
    private final MemberRepository memberRepository;

    @Override
    public String reIssueAccessToken(String refreshToken) {
        if (!redisService.isExistRefreshToken(refreshToken)) {
            throw new InvalidTokenException("리프레시 토큰이 유효하지 않습니다.");
        }
        String email = jwtUtil.extractClaim(refreshToken, Claims::getSubject);
        Member findMember = memberRepository.findByEmail(email).orElseThrow(() -> new MemberNotFoundException("멤버가 존재하지 않습니다."));
        return jwtUtil.generateAccessToken(findMember.getEmail(), findMember.getRole());
    }
}
