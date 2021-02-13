package sigproxy.smpp.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sigproxy.smpp.server.dto.SMPPSessionConfigDto;

public interface SMPPSessionConfigDtoRepository extends MongoRepository<SMPPSessionConfigDto, Integer> {
}
