package sigproxy.smpp.server.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sigproxy.smpp.server.dto.SMPPSessionConfigDto;
import sigproxy.smpp.server.model.SMPPSessionConfig;
import sigproxy.smpp.server.service.SMPPSessionConfigService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class SMPPSessionConfigResolver implements GraphQLQueryResolver {
    private final SMPPSessionConfigService smppSessionConfigService;

    public List<SMPPSessionConfigDto> findAll() {
        log.info("Retrieving smpp session configuration!");
        return smppSessionConfigService.findAll().stream().map(data -> {
            return SMPPSessionConfigDto.builder()
                                       .id(data.getId())
                                       .name(data.getConfig().getName())
                                       .windowSize(data.getConfig().getWindowSize())
                                       .type(data.getConfig().getType())
                                       .systemId(data.getConfig().getSystemId())
                                       .password(data.getConfig().getPassword())
                                       .systemType(data.getConfig().getSystemType())
                                       .interfaceVersion(data.getConfig().getInterfaceVersion())
                                       .addressRange(data.getConfig().getAddressRange())
                                       .bindTimeout(data.getConfig().getBindTimeout())
                                       .windowWaitTimeout(data.getConfig().getWindowWaitTimeout())
                                       .requestExpiryTimeout(data.getConfig().getRequestExpiryTimeout())
                                       .windowMonitorInterval(data.getConfig().getWindowMonitorInterval())
                                       .countersEnabled(data.getConfig().isCountersEnabled()).build();
            }).collect(Collectors.toList());
    }
}
