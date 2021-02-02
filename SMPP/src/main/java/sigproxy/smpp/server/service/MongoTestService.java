package sigproxy.smpp.server.service;

import sigproxy.smpp.server.model.Message;

import java.util.List;
import java.util.Optional;

public interface MongoTestService {
    List<Message> findAll();
    Optional<Message> findById(int id);
    int create(Message newMessage);
    int update(Message updatedMessage);
    boolean delete(int id);
}
