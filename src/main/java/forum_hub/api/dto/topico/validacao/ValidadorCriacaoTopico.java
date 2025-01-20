package forum_hub.api.dto.topico.validacao;

import forum_hub.api.dto.topico.DadosCriacaoTopico;

public interface ValidadorCriacaoTopico {
    void validar(DadosCriacaoTopico dados);
}
