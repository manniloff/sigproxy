package sigproxy.smpp.server.service;

import sigproxy.smpp.server.dto.SMPPSessionConfigDto;
import sigproxy.smpp.server.dto.SMPPSessionConfigDtoInput;

import java.util.List;
import java.util.Optional;

public interface SMPPSessionConfigDtoService {
    List<SMPPSessionConfigDto> findAll();

    Optional<SMPPSessionConfigDto> findById(int id);

    SMPPSessionConfigDto create(SMPPSessionConfigDtoInput newSMPPSessionConfig);

    Optional<SMPPSessionConfigDto> update(SMPPSessionConfigDtoInput updatedSMPPSessionConfig, int id);

    String deleteById(int id);
}
