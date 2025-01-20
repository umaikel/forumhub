package forum_hub.api.service;

import forum_hub.api.dto.topico.DadosCriacaoTopico;
import forum_hub.api.dto.topico.DadosListagemTopico;
import forum_hub.api.dto.topico.DadosTopicoAtualizacao;
import forum_hub.api.dto.topico.DadosTopicoResponse;
import forum_hub.api.dto.topico.validacao.ValidadorCriacaoTopico;
import forum_hub.api.model.entidades.Curso;
import forum_hub.api.model.entidades.Topico;
import forum_hub.api.model.entidades.Usuario;
import forum_hub.api.repository.CursoRepository;
import forum_hub.api.repository.TopicoRepository;
import forum_hub.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<ValidadorCriacaoTopico> validadorCriacaoTopico;

    @Transactional
    public DadosTopicoResponse cadastrar(DadosCriacaoTopico dados) {

        validadorCriacaoTopico.forEach(v -> v.validar(dados));

        Usuario autor = usuarioRepository.getReferenceById(dados.idAutor());
        Curso curso = cursoRepository.getReferenceById(dados.idCurso());

        Topico topico = new Topico(
                dados.titulo(),
                dados.mensagem(),
                autor,
                curso
        );
        repository.save(topico);

        var response = new DadosTopicoResponse(topico);
        return response;
    }


    public Page<DadosListagemTopico> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemTopico::new);
    }

    public DadosListagemTopico detalhar(Long id) {
        Topico topico = repository.getReferenceById(id); // corrigir
        DadosListagemTopico topicoDetalhado = new DadosListagemTopico(topico);
        return topicoDetalhado;
    }

    @Transactional
    public DadosListagemTopico atualizar(Long id, DadosTopicoAtualizacao dados){
        Topico topico = repository.getReferenceById(id); // corrigir

        if (dados.titulo() != null && !dados.titulo().equals("")){
            topico.setTitulo(dados.titulo());
        }

        if (dados.mensagem() != null && !dados.mensagem().equals("")){
            topico.setMensagem(dados.mensagem());
        }

        if (dados.idCurso() != null && cursoRepository.existsById(dados.idCurso())) {
            topico.setCurso(cursoRepository.getReferenceById(dados.idCurso()));
        }

        return new DadosListagemTopico(topico);
    }

    @Transactional
    public void deletar(Long id) {
        Optional<Topico> topico = repository.findById(id);
        if (topico.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
