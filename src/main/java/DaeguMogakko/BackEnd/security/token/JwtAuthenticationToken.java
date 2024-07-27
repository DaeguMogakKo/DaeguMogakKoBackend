package DaeguMogakko.BackEnd.security.token;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String authToken;
    private final Object principal;
    private final Object credentials;

    public JwtAuthenticationToken(String authToken) {
        super(null);
        this.authToken = authToken;
        this.principal = null;
        this.credentials = null;
        setAuthenticated(false);
    }

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials) {
        super(authorities);
        this.authToken = null;
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return authToken;
    }
}
