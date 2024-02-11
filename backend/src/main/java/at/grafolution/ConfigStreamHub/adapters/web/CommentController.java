package at.grafolution.ConfigStreamHub.adapters.web;

import at.grafolution.ConfigStreamHub.adapters.messaging.KafkaProducer;
import at.grafolution.ConfigStreamHub.application.service.CommentService;
import at.grafolution.ConfigStreamHub.domain.model.Comment;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;
    private final KafkaProducer kafkaProducer;

    public CommentController(CommentService commentService, KafkaProducer kafkaProducer) {
        this.commentService = commentService;
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Comment>> createComment(@PathVariable String postId, @RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(comment);
        // Notify after successful creation of the comment
        kafkaProducer.notifyNewComment(createdComment);

        EntityModel<Comment> resource = EntityModel.of(createdComment);
        resource.add(linkTo(methodOn(CommentController.class).getCommentById(createdComment.getId())).withRel("self"));
        resource.add(linkTo(methodOn(CommentController.class).getAllCommentsFromBlogPost(postId)).withRel("all-comments"));
        resource.add(linkTo(methodOn(CommentController.class).deleteComment(createdComment.getId())).withRel("delete"));
        resource.add(linkTo(methodOn(CommentController.class).updateComment(postId, createdComment.getId(), comment)).withRel("update"));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(postId, createdComment.getId()).toUri();

        return ResponseEntity.created(location).body(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById( @PathVariable String id) {
        return commentService.findCommentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllCommentsFromBlogPost(@PathVariable String postId) {
        List<Comment> comments = commentService.findAllCommentsByBlogPost(postId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String postId, @PathVariable String id, @RequestBody Comment comment) {
        return commentService.updateComment( id, comment)
                .map(updatedComment -> {
                    kafkaProducer.notifyUpdatedComment(updatedComment);
                    return ResponseEntity.ok(updatedComment);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
