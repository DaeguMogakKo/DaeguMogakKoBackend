package DaeguMogakko.BackEnd.exception;

public class MemberPasswordException extends RuntimeException{
    public MemberPasswordException(String message) {
        super(message);
    }
}
