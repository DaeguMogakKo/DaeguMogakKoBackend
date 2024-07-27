package DaeguMogakko.BackEnd.login;

import DaeguMogakko.BackEnd.exception.MemberNotFoundException;
import DaeguMogakko.BackEnd.exception.MemberPasswordException;
import DaeguMogakko.BackEnd.member.Member;
import DaeguMogakko.BackEnd.member.MemberRepository;
import DaeguMogakko.BackEnd.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;


    @Override
    public void isCorrectEmail(String email) {
        Boolean result = memberRepository.existsByEmail(email);
        if(!result)
            throw new MemberNotFoundException("멤버가 존재하지 않습니다.");
    }

    @Override
    public void isCorrectPassword(String email, String inputPassword) {
        memberRepository.findByEmail(email).orElseThrow(() -> new MemberPasswordException("비밀번호가 틀렸습니다."));
    }

    @Override
    public String issueAccessToken(String email, String role) {
        return jwtUtil.generateAccessToken(email, role);
    }

    @Override
    public String issueRefreshToken(String email) {
        return jwtUtil.generateRefreshToken(email);
    }


}
