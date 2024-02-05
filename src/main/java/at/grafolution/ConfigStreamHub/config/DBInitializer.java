package at.grafolution.ConfigStreamHub.config;

import at.grafolution.ConfigStreamHub.adapters.persistence.CommentRepository;
import at.grafolution.ConfigStreamHub.domain.model.Comment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer  implements CommandLineRunner {
    private final CommentRepository commentRepository;

    public DBInitializer(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (commentRepository.count() == 0) {
            commentRepository.save(new Comment("Felix", "Kommentar 1"));
            commentRepository.save(new Comment("felix","Kommentar 2"));
            // Füge weitere Basisdaten hinzu
        }
    }
}
