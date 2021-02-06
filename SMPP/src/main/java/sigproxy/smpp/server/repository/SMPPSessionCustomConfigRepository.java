package sigproxy.smpp.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sigproxy.smpp.server.model.SMPPSessionConfig;

import java.util.Optional;

@Repository
public interface SMPPSessionCustomConfigRepository extends MongoRepository<SMPPSessionConfig, Integer> {
    Optional<SMPPSessionConfig> findByConfigName(String configName);
}
