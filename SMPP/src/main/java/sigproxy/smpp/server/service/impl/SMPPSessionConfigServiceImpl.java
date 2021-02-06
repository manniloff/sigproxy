package sigproxy.smpp.server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sigproxy.smpp.server.model.SMPPSessionConfig;
import sigproxy.smpp.server.repository.SMPPSessionCustomConfigRepository;
import sigproxy.smpp.server.service.SMPPSessionConfigService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SMPPSessionConfigServiceImpl implements SMPPSessionConfigService {
    private final SMPPSessionCustomConfigRepository sessionConfigRepository;

    @Override
    public List<SMPPSessionConfig> findAll() {
        return sessionConfigRepository.findAll();
    }

    @Override
    public Optional<SMPPSessionConfig> findById(int id) {
        return sessionConfigRepository.findById(id);
    }

    @Override
    public int create(SMPPSessionConfig newSMPPSessionConfig) {
        sessionConfigRepository.findByConfigName(newSMPPSessionConfig.getConfig().getName())
                               .ifPresent(config -> {
                                   log.error("Config with name {} exists, try again", config.getConfig().getName());
                                   throw new RuntimeException("Configuration with " + config
                                           .getConfig().getName() + " exists, change configuration name and try again");
                               });

        return sessionConfigRepository.save(newSMPPSessionConfig).getId();
    }

    @Override
    public Optional<SMPPSessionConfig> update(SMPPSessionConfig updatedSMPPSessionConfig, int id) {
        sessionConfigRepository.findById(id)
                               .map(config -> {
                                   config.setConfig(updatedSMPPSessionConfig.getConfig());
                                   return Optional.of(sessionConfigRepository.save(config));
                               }).orElseThrow(() -> new RuntimeException("No Content"));
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) {
        sessionConfigRepository.findById(id)
                               .ifPresent(config -> sessionConfigRepository.deleteById(config.getId()));
    }
}
