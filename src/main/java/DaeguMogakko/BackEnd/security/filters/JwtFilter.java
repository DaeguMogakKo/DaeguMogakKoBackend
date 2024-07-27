package DaeguMogakko.BackEnd.security.filters;

import DaeguMogakko.BackEnd.security.token.JwtAuthenticationToken;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

public class JwtFilter extends AbstractAuthenticationProcessingFilter {

    public JwtFilter() {
        super(new NegatedRequestMatcher(
                new OrRequestMatcher(
                        new AntPathRequestMatcher("/v1/auth/login", "POST"),
                        new AntPathRequestMatcher("/v1/auth/signup", "POST"),
                        new AntPathRequestMatcher("/v1/token/reissue", "POST")
                )));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String authToken = getJwtFromHeader(request);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(authToken);

        return getAuthenticationManager().authenticate(jwtAuthenticationToken);
    }

    private String getJwtFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new JwtException("JWT Token이 존재하지 않습니다.");
        }
        return header.substring(7);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        chain.doFilter(request, response);
    }
}
