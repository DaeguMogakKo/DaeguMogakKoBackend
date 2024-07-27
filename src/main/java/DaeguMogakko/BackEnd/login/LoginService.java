package DaeguMogakko.BackEnd.login;

public interface LoginService {
    void isCorrectEmail(String email);
    void isCorrectPassword(String email, String inputPassword);
    String issueAccessToken(String email);
    String issueRefreshToken(String email);
}
