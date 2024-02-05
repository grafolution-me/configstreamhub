package at.grafolution.ConfigStreamHub.adapters.persistence;

import at.grafolution.ConfigStreamHub.domain.model.Comment;
import at.grafolution.ConfigStreamHub.port.CommentRepositoryPort;
import org.springframework.data.mongodb.repository.MongoRepository;

// adapter implements CommentRepositoryPort --> in this case not needed, just for completness
public interface CommentRepository extends MongoRepository<Comment, String>, CommentRepositoryPort {
    // Standard CRUD-Operationen werden automatisch von Spring Data bereitgestellt
}
