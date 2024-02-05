package at.grafolution.ConfigStreamHub.adapters.messaging;

import at.grafolution.ConfigStreamHub.domain.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class KafkaConsumer {

    @Autowired
    private KafkaTemplate<String, Comment> kafkaTemplate;



    @KafkaListener(topics = "commentCreatedTopic", groupId = "blog-notification-group")
    public void listenForNewBlogPosts(Comment comment) {
        System.out.println("new comment emitted from " + comment.getAuthor());
    }
}

