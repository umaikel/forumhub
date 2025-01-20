package forum_hub.api.dto.topico;

import forum_hub.api.model.entidades.Topico;

import java.time.LocalDateTime;

public record DadosTopicoResponse(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataDeCriacao
) {

    public DadosTopicoResponse(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataDeCriacao());
    }
}
