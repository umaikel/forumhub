package forum_hub.api.dto.topico;

import forum_hub.api.model.entidades.Topico;
import forum_hub.api.model.enums.Status;

import java.time.LocalDateTime;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataDeCriacao,
        Status status,
        String autor,
        String curso
) {

    public DadosListagemTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataDeCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        );
    }
}
