package DaeguMogakko.BackEnd.jwt;

public interface JwtService {

    String reIssueAccessToken(String refreshToken);
}
