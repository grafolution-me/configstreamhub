package at.grafolution.ConfigStreamHub.adapters.messaging;

import at.grafolution.ConfigStreamHub.domain.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Comment> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;
    public void notifyNewComment(Comment comment) {
        kafkaTemplate.send("commentCreatedTopic", comment);
    }
    public void notifyDeletedComment(String id) {
        kafkaTemplateString.send("commentDeletedTopic", id);
    }

    public void notifyUpdatedComment(Comment comment) {
        kafkaTemplate.send("commentUpdatedTopic", comment);
    }
}
