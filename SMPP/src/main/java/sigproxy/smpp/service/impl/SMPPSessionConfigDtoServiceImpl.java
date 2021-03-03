package sigproxy.smpp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sigproxy.smpp.dto.SMPPSessionConfigDto;
import sigproxy.smpp.dto.SMPPSessionConfigDtoInput;
import sigproxy.smpp.repository.SMPPSessionConfigDtoRepository;
import sigproxy.smpp.service.SMPPSessionConfigDtoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SMPPSessionConfigDtoServiceImpl implements SMPPSessionConfigDtoService {
    private final SMPPSessionConfigDtoRepository sessionConfigRepository;

    @Override
    public List<SMPPSessionConfigDto> findAll() {
        return sessionConfigRepository.findAll();
    }

    @Override
    public Optional<SMPPSessionConfigDto> findById(int id) {
        return sessionConfigRepository.findById(id);
    }

    @Override
    public SMPPSessionConfigDto create(SMPPSessionConfigDtoInput smppSessionConfigDtoInput) {
        sessionConfigRepository.findById(smppSessionConfigDtoInput.getId())
                               .ifPresent(config -> {
                                   log.error("Config with name {} exists, try again", config.getName());
                                   throw new RuntimeException("Configuration with " + config
                                           .getName() + " exists, change configuration name and try again");
                               });
        SMPPSessionConfigDto sessionConfigDto = SMPPSessionConfigDto.builder()
                                                                    .id(smppSessionConfigDtoInput.getId())
                                                                    .name(smppSessionConfigDtoInput.getName())
                                                                    .windowSize(smppSessionConfigDtoInput.getWindowSize())
                                                                    .type(smppSessionConfigDtoInput.getType())
                                                                    .systemId(smppSessionConfigDtoInput.getSystemId())
                                                                    .password(smppSessionConfigDtoInput.getPassword())
                                                                    .systemType(smppSessionConfigDtoInput.getSystemType())
                                                                    .interfaceVersion(smppSessionConfigDtoInput.getInterfaceVersion())
                                                                    .addressRange(smppSessionConfigDtoInput.getAddressRange())
                                                                    .bindTimeout(smppSessionConfigDtoInput.getBindTimeout())
                                                                    .windowWaitTimeout(smppSessionConfigDtoInput.getWindowWaitTimeout())
                                                                    .requestExpiryTimeout(smppSessionConfigDtoInput.getRequestExpiryTimeout())
                                                                    .windowMonitorInterval(smppSessionConfigDtoInput.getWindowMonitorInterval())
                                                                    .countersEnabled(smppSessionConfigDtoInput.isCountersEnabled())
                                                                    .active(smppSessionConfigDtoInput.isActive()).build();
        return sessionConfigRepository.save(sessionConfigDto);
    }

    @Override
    public Optional<SMPPSessionConfigDto> update(SMPPSessionConfigDtoInput updatedSMPPSessionConfig, int id) {
        sessionConfigRepository.findById(id)
                               .map(config -> {
                                   config.builder()
                                         .name(updatedSMPPSessionConfig.getName())
                                         .windowSize(updatedSMPPSessionConfig.getWindowSize())
                                         .type(updatedSMPPSessionConfig.getType())
                                         .systemId(updatedSMPPSessionConfig.getSystemId())
                                         .password(updatedSMPPSessionConfig.getPassword())
                                         .systemType(updatedSMPPSessionConfig.getSystemType())
                                         .interfaceVersion(updatedSMPPSessionConfig.getInterfaceVersion())
                                         .addressRange(updatedSMPPSessionConfig.getAddressRange())
                                         .bindTimeout(updatedSMPPSessionConfig.getBindTimeout())
                                         .windowWaitTimeout(updatedSMPPSessionConfig.getWindowWaitTimeout())
                                         .requestExpiryTimeout(updatedSMPPSessionConfig.getRequestExpiryTimeout())
                                         .windowMonitorInterval(updatedSMPPSessionConfig.getWindowMonitorInterval())
                                         .countersEnabled(updatedSMPPSessionConfig.isCountersEnabled())
                                         .active(updatedSMPPSessionConfig.isActive()).build();
                                   return Optional.of(sessionConfigRepository.save(config));
                               }).orElseThrow(() -> new RuntimeException("No Content"));
        return Optional.empty();
    }

    @Override
    public String deleteById(int id) {
        sessionConfigRepository.findById(id)
                               .ifPresent(config -> {
                                   sessionConfigRepository.deleteById(config.getId());
                               });
        return "Deleted";
    }
}
