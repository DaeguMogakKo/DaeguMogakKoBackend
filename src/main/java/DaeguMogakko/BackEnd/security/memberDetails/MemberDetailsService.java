package DaeguMogakko.BackEnd.security.memberDetails;

import DaeguMogakko.BackEnd.security.jwt.JwtUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String authToken) throws UsernameNotFoundException {
        String memberEmail = jwtUtil.extractClaim(authToken, Claims::getSubject);
        String memberRoles = jwtUtil.extractClaim(authToken, claims -> claims.get("roles", String.class));
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(memberRoles));

        return new MemberDetails(memberEmail, authorities);
    }
}
