package forum_hub.api.controller;

import forum_hub.api.dto.usuario.DadosAtualizacaoUsuario;
import forum_hub.api.dto.usuario.DadosCadastroUsuario;
import forum_hub.api.dto.usuario.DadosListagemUsuario;
import forum_hub.api.dto.usuario.DadosUsuarioResponse;
import forum_hub.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DadosUsuarioResponse> cadastro(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuarioCadastrado = usuarioService.cadastrar(dados);
        var uri = uriBuilder.path("/usuarios")
                .build()
                .toUri();

        return ResponseEntity.created(uri).body(usuarioCadastrado);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listarUsuarios(@PageableDefault(size = 10) Pageable paginacao){
        var page = usuarioService.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarUsuario(@PathVariable Long id, @RequestBody DadosAtualizacaoUsuario dados){
        DadosListagemUsuario response = usuarioService.atualizar(id, dados);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
