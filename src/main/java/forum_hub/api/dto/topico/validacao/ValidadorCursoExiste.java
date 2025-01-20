package forum_hub.api.dto.topico.validacao;

import forum_hub.api.dto.topico.DadosCriacaoTopico;
import forum_hub.api.model.entidades.Curso;
import forum_hub.api.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorCursoExiste implements ValidadorCriacaoTopico {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void validar(DadosCriacaoTopico dados) {
        Optional<Curso> cursoExiste = cursoRepository.findById(dados.idCurso());
        if (cursoExiste.isEmpty()) {
            throw new EntityNotFoundException();
        }
    }
}
