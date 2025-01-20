package forum_hub.api.dto.topico.validacao;

import forum_hub.api.dto.topico.DadosCriacaoTopico;
import forum_hub.api.model.entidades.Usuario;
import forum_hub.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorAutorExiste implements ValidadorCriacaoTopico {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(DadosCriacaoTopico dados) {
        Optional<Usuario> autorExiste = usuarioRepository.findById(dados.idAutor());
        if (autorExiste.isEmpty()) {
            throw new EntityNotFoundException();
        }
    }
}
