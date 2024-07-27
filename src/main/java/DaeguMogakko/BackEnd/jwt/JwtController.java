package DaeguMogakko.BackEnd.jwt;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/token")
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/reissue")
    public ResponseEntity<Map<String, String>> reissue(@RequestHeader("refreshToken") String request) {
        String accessToken = jwtService.reIssueAccessToken(request);
        return new ResponseEntity<>(Map.of("accessToken", accessToken), HttpStatus.OK);
    }
}
