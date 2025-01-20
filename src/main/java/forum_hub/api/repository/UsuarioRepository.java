package forum_hub.api.repository;

import forum_hub.api.model.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailIgnoreCase(String email);

    @Query("select u from Usuario u where u.ativo = true")
    Page<Usuario> findAllAtivos(Pageable paginacao);

    UserDetails findByEmail(String email);
}
