package DaeguMogakko.BackEnd.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Boolean isExistEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}
