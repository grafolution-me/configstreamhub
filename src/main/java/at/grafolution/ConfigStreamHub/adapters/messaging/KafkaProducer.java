package at.grafolution.ConfigStreamHub.adapters.messaging;

import at.grafolution.ConfigStreamHub.domain.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Comment> kafkaTemplate;
    public void notifyNewComment(Comment comment) {
        kafkaTemplate.send("commentCreatedTopic", comment);
    }
}
