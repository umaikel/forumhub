package forum_hub.api.dto.usuario;

import forum_hub.api.model.entidades.Usuario;

public record DadosUsuarioResponse(Long id, String nome ,String email) {

    public DadosUsuarioResponse(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

}
