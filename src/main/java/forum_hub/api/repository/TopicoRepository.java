package forum_hub.api.repository;

import forum_hub.api.model.entidades.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndMensagemIgnoreCase(String titulo, String mensagem);
}
