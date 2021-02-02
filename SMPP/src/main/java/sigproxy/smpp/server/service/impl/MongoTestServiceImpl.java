package sigproxy.smpp.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sigproxy.smpp.server.model.Message;
import sigproxy.smpp.server.repository.MongoTestRepository;
import sigproxy.smpp.server.service.MongoTestService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MongoTestServiceImpl implements MongoTestService {
    private final MongoTestRepository mongoTestRepository;


    public List<Message> findAll() {
        return mongoTestRepository.findAll();
    }

    public Optional<Message> findById(int id) {
        return mongoTestRepository.findById(id);
    }

    public int create(Message newMessage) {
        try {
            return mongoTestRepository.save(newMessage).getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int update(Message updatedMessage) {
        return mongoTestRepository.save(updatedMessage).getId();
    }

    public boolean delete(int id) {
        Optional<Message> message = mongoTestRepository.findById(id);

        if(message.isPresent()) {
            mongoTestRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
