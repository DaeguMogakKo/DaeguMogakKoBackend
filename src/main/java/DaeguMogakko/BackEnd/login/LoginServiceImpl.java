package DaeguMogakko.BackEnd.login;

import DaeguMogakko.BackEnd.exception.DuplicateMemberException;
import DaeguMogakko.BackEnd.exception.MemberNotFoundException;
import DaeguMogakko.BackEnd.exception.MemberPasswordException;
import DaeguMogakko.BackEnd.member.Member;
import DaeguMogakko.BackEnd.member.MemberRepository;
import DaeguMogakko.BackEnd.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;


    @Override
    public void isCorrectEmail(String email) {
        if (!memberRepository.existsByEmail(email)) {
            throw new MemberNotFoundException("멤버가 존재하지 않습니다.");
        }
    }

    @Override
    public void isCorrectPassword(String email, String inputPassword) {
        memberRepository.findByEmail(email).orElseThrow(() -> new MemberPasswordException("비밀번호가 틀렸습니다."));
    }

    @Override
    public String issueAccessToken(String email) {
        Member findMember = memberRepository.findByEmail(email).get();
        return jwtUtil.generateAccessToken(email, findMember.getRole());
    }

    @Override
    public String issueRefreshToken(String email) {
        return jwtUtil.generateRefreshToken(email);
    }

    @Override
    public void signup(String email, String password) {
        isExistEmail(email);
        memberRepository.save(Member.builder()
                .email(email)
                .password(password)
                .role("ROLE_USER")
                .build());
    }

    private void isExistEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateMemberException("이미 존재하는 이메일입니다.");
        }
    }
}
