package sigproxy.smpp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sigproxy.smpp.dto.SMPPSessionConfigDto;

public interface SMPPSessionConfigDtoRepository extends MongoRepository<SMPPSessionConfigDto, Integer> {
}
