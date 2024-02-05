package at.grafolution.ConfigStreamHub.adapters.web;

import at.grafolution.ConfigStreamHub.adapters.messaging.KafkaProducer;
import at.grafolution.ConfigStreamHub.domain.model.Comment;
import at.grafolution.ConfigStreamHub.application.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final KafkaProducer kafkaProducer;

    public CommentController(CommentService commentService, KafkaProducer kafkaProducer) {
        this.commentService = commentService;
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        this.kafkaProducer.notifyNewComment(comment);
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.ok(createdComment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        return commentService.findCommentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        this.kafkaProducer.notifyNewComment(new Comment("bla" , "test"));
        List<Comment> comments = commentService.findAllComments();
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable UUID id, @RequestBody Comment comment) {
        return commentService.updateComment(id, comment)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}