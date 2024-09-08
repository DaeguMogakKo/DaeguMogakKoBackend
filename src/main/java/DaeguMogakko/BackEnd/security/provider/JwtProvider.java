package DaeguMogakko.BackEnd.security.provider;

import DaeguMogakko.BackEnd.jwt.JwtUtil;
import DaeguMogakko.BackEnd.security.memberDetails.MemberDetails;
import DaeguMogakko.BackEnd.security.memberDetails.MemberDetailsService;
import DaeguMogakko.BackEnd.security.token.JwtAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtProvider implements AuthenticationProvider {

    private final JwtUtil jwtUtil;
    private final MemberDetailsService memberDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String authToken = (String) authentication.getPrincipal();
        jwtUtil.validateToken(authToken);
        MemberDetails memberDetails = (MemberDetails) memberDetailsService.loadUserByUsername(authToken);
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(
                memberDetails.getAuthorities(),
                memberDetails.getUsername(),
                null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return new JwtAuthenticationToken(memberDetails.getAuthorities(), memberDetails.getUsername(), null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
