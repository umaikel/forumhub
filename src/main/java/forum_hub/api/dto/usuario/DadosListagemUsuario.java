package forum_hub.api.dto.usuario;

import forum_hub.api.model.entidades.Usuario;

public record DadosListagemUsuario(String nome, String email) {

    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getNome(), usuario.getEmail());
    }
}
