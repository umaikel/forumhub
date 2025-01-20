package forum_hub.api.infra.exception;

public class TopicoExistException extends RuntimeException {

    public TopicoExistException(String mensagem) {
        super(mensagem);
    }
}