package sigproxy.smpp.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sigproxy.smpp.server.model.Message;

@Repository
public interface MongoTestRepository extends MongoRepository<Message, Integer> {
}
