package forum_hub.api.repository;

import forum_hub.api.model.entidades.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
}
