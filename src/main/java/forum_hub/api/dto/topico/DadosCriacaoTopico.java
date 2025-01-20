package forum_hub.api.dto.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriacaoTopico(

        @NotBlank(message = "Campo 'titulo' faltando ou vazio.")
        String titulo,

        @NotBlank(message = "Campo 'mensagem' faltando ou vazio.")
        String mensagem,

        @NotNull(message = "Campo 'idAutor' faltando ou vazio.")
        Long idAutor,

        @NotNull(message = "Campo 'idCurso' faltando ou vazio.")
        Long idCurso
) {
}
