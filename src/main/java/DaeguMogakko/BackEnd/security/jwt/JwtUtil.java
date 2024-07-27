package DaeguMogakko.BackEnd.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret_key}")
    private String SECRET_KEY;

    @Value("${jwt.access_token_validity}")
    private int ACCESS_TOKEN_VALIDITY;

    @Value("${jwt.refresh_token_validity}")
    private int REFRESH_TOKEN_VALIDITY;

    private Key key;
    private JwtParser jwtParser;

    @PostConstruct
    public void initJwtUtil() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
        this.jwtParser = Jwts.parser().verifyWith((SecretKey) this.key).build();
    }

    public Claims extractAllClaims(String token) {
        return (Claims) jwtParser.parse(token).getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateAccessToken(String memberEmail, String role) {
        return Jwts.builder()
                .subject(memberEmail)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY * 1000L))
                .claim("roles", role)
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String memberEmail) {
        return Jwts.builder()
                .subject(memberEmail)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY * 1000L))
                .signWith(key)
                .compact();
    }


    public void validateToken(String token) {
        parseToken(token, jwtParser);
    }

    private static void parseToken(String token, JwtParser jwtParser) {
        try {
            jwtParser.parse(token);
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("형식이 올바르지 않습니다.");
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, "만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("지원하지 않는 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("토큰의 내용이 비어 있습니다.");
        }
    }


}
