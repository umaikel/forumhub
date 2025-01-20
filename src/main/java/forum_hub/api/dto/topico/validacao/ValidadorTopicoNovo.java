package forum_hub.api.dto.topico.validacao;

import forum_hub.api.dto.topico.DadosCriacaoTopico;
import forum_hub.api.infra.exception.TopicoExistException;
import forum_hub.api.model.entidades.Topico;
import forum_hub.api.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorTopicoNovo implements ValidadorCriacaoTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DadosCriacaoTopico dados) {
        Optional<Topico> topicoExistente = topicoRepository.findByTituloAndMensagemIgnoreCase(dados.titulo(), dados.mensagem());
        if (topicoExistente.isPresent()) {
            throw new TopicoExistException("");
        }
    }
}
