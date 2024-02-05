package at.grafolution.ConfigStreamHub.application.service;

import at.grafolution.ConfigStreamHub.adapters.persistence.CommentRepository;
import at.grafolution.ConfigStreamHub.domain.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        // Implementieren Sie hier die Logik zum Speichern des Kommentars in der Datenbank
        return commentRepository.save(comment);
    }

    public Optional<Comment> findCommentById(String id) {
        // Implementieren Sie hier die Logik zum Abrufen eines Kommentars anhand seiner ID
        return commentRepository.findById(id);
    }

    public List<Comment> findAllComments() {
        // Implementieren Sie hier die Logik zum Abrufen aller Kommentare
        return commentRepository.findAll();
    }

    public Optional<Comment> updateComment(UUID id, Comment comment) {
        // Implementieren Sie hier die Logik zum Aktualisieren eines Kommentars
        // Normalerweise würden Sie zuerst prüfen, ob der Kommentar existiert
        return Optional.of(commentRepository.save(comment));
    }

    public void deleteComment(String id) {
        // Implementieren Sie hier die Logik zum Löschen eines Kommentars
        commentRepository.deleteById(id);
    }
}