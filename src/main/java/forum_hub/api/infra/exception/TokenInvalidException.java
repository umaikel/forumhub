package forum_hub.api.infra.exception;

public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(String s) {
        super(s);
    }
}
