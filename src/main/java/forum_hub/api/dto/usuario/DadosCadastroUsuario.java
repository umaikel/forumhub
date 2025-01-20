package forum_hub.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank(message = "Campo 'nome' faltando ou vazio.")
        String nome,

        @NotBlank(message = "Campo 'login' faltando ou vazio.")
        String email,

        @NotBlank(message = "Campo 'senha' faltando ou vazio")
        String senha
) {
}
