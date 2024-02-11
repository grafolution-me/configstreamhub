package at.grafolution.ConfigStreamHub.application.service;

import at.grafolution.ConfigStreamHub.adapters.persistence.CommentRepository;
import at.grafolution.ConfigStreamHub.domain.model.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        if(Objects.equals(comment.getId(), "")) {
            throw new IllegalArgumentException("Comment ID must not be null or empty");
        }
        return commentRepository.save(comment);
    }

    public Optional<Comment> findCommentById(String id) {
        return commentRepository.findById(id);
    }

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }
    public List<Comment> findAllCommentsByBlogPost(String blogPostId) {

        if (blogPostId == null || blogPostId.isEmpty()) {
            throw new IllegalArgumentException("BlogPost ID must not be null or empty");
        }

        // Optional: check blogPostId with BlogPostRepository


        // Abrufen und Rückgabe der Kommentare
        return commentRepository.findAllByPostId(blogPostId);
    }
    public Optional<Comment> updateComment(String commentId, Comment comment) {
        return commentRepository.findById(commentId).map((existingComment)->{
            try {
                BeanUtils.copyProperties(existingComment, comment);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Optional.of(commentRepository.save(existingComment));
        }).orElse(Optional.empty());
    }

    public void deleteComment(String id) {
        // Implementieren Sie hier die Logik zum Löschen eines Kommentars
        commentRepository.deleteById(id);
    }
}