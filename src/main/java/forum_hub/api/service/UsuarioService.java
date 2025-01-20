package forum_hub.api.service;

import forum_hub.api.dto.usuario.DadosAtualizacaoUsuario;
import forum_hub.api.dto.usuario.DadosCadastroUsuario;
import forum_hub.api.dto.usuario.DadosListagemUsuario;
import forum_hub.api.dto.usuario.DadosUsuarioResponse;
import forum_hub.api.infra.exception.ValidacaoException;
import forum_hub.api.model.entidades.Usuario;
import forum_hub.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public DadosUsuarioResponse cadastrar(DadosCadastroUsuario dados) {

        if (repository.findByEmailIgnoreCase(dados.email()).isPresent()) {
            throw new ValidacaoException("E-mail já cadastrado.");
        }

        Usuario usuario = new Usuario(
                dados.nome(),
                dados.email(),
                passwordEncoder.encode(dados.senha())
        );
        repository.save(usuario);

        DadosUsuarioResponse response = new DadosUsuarioResponse(usuario);
        return response;
    }

    public Page<DadosListagemUsuario> listar(Pageable paginacao) {
        return repository.findAllAtivos(paginacao).map(DadosListagemUsuario::new);
    }


    @Transactional
    public DadosListagemUsuario atualizar(Long id, DadosAtualizacaoUsuario dados) {
        Optional<Usuario> autorOpt = repository.findById(id);

        if (autorOpt.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Usuario autor = autorOpt.get();
        if (dados.nome() != null && !dados.nome().equals("")) {
            autor.setNome(dados.nome());
        }

        if (dados.senha() != null && !dados.senha().equals("")) {
            autor.setSenha(passwordEncoder.encode(dados.senha()));
        }

        return new DadosListagemUsuario(autor);
    }

    @Transactional
    public void deletar(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            usuario.get().setAtivo(false);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
