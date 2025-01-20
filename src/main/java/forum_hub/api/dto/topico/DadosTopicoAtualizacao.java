package forum_hub.api.dto.topico;

public record DadosTopicoAtualizacao(
        String titulo,
        String mensagem,
        Long idCurso
) {
}
