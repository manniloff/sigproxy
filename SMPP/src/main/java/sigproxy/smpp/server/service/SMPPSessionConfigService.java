package sigproxy.smpp.server.service;

import sigproxy.smpp.server.model.SMPPSessionConfig;

import java.util.List;
import java.util.Optional;

public interface SMPPSessionConfigService {
    List<SMPPSessionConfig> findAll();

    Optional<SMPPSessionConfig> findById(int id);

    int create(SMPPSessionConfig newSMPPSessionConfig);

    Optional<SMPPSessionConfig> update(SMPPSessionConfig updatedSMPPSessionConfig, int id);

    void deleteById(int id);
}
