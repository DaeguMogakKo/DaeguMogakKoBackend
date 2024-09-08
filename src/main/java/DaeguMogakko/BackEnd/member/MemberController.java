package DaeguMogakko.BackEnd.member;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/check")
    public ResponseEntity<Map<String, Boolean>> isExistEmail(@RequestParam String email) {
        Boolean exist = memberService.isExistEmail(email);
        return new ResponseEntity<>(Map.of("exist", exist), HttpStatus.OK);
    }
}
