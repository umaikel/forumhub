package forum_hub.api.controller;

import forum_hub.api.dto.topico.DadosCriacaoTopico;
import forum_hub.api.dto.topico.DadosListagemTopico;
import forum_hub.api.dto.topico.DadosTopicoAtualizacao;
import forum_hub.api.dto.topico.DadosTopicoResponse;
import forum_hub.api.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity criarTopico(@RequestBody @Valid DadosCriacaoTopico dados, UriComponentsBuilder uriBuilder) {
        DadosTopicoResponse response = topicoService.cadastrar(dados);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicos(@PageableDefault(size = 10) Pageable paginacao){
        var page = topicoService.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id){
        var topico = topicoService.detalhar(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosTopicoAtualizacao dados) {
        DadosListagemTopico topicoAtulizado = topicoService.atualizar(id, dados);
        return ResponseEntity.ok(topicoAtulizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarTopico(@PathVariable Long id) {
        topicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
