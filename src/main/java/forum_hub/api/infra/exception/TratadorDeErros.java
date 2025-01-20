package forum_hub.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(TopicoExistException.class)
    public ResponseEntity topicoExiste400(TopicoExistException exception) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity validacaoException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity tokenException(TokenInvalidException exception) {
        return ResponseEntity.badRequest().body(new exceptionDto(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException exception) {
        var erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    public record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
