package DaeguMogakko.BackEnd.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MemberNotFoundException extends UsernameNotFoundException {
    public MemberNotFoundException(String msg) {
        super(msg);
    }
}
