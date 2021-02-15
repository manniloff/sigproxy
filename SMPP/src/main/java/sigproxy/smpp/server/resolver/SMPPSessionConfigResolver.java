package sigproxy.smpp.server.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import sigproxy.smpp.server.dto.SMPPSessionConfigDto;
import sigproxy.smpp.server.dto.SMPPSessionConfigDtoInput;
import sigproxy.smpp.server.service.SMPPSessionConfigDtoService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class SMPPSessionConfigResolver implements GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {
    private final SMPPSessionConfigDtoService smppSessionConfigService;
    private ConcurrentHashMap<Integer, FluxSink<SMPPSessionConfigDto>> subscribers = new ConcurrentHashMap<>();

    public List<SMPPSessionConfigDto> findAll() {
        log.info("Retrieving list of smpp session configurations");
        return smppSessionConfigService.findAll().stream().map(data -> {
            return SMPPSessionConfigDto.builder()
                                       .id(data.getId())
                                       .name(data.getName())
                                       .windowSize(data.getWindowSize())
                                       .type(data.getType())
                                       .systemId(data.getSystemId())
                                       .password(data.getPassword())
                                       .systemType(data.getSystemType())
                                       .interfaceVersion(data.getInterfaceVersion())
                                       .addressRange(data.getAddressRange())
                                       .bindTimeout(data.getBindTimeout())
                                       .windowWaitTimeout(data.getWindowWaitTimeout())
                                       .requestExpiryTimeout(data.getRequestExpiryTimeout())
                                       .windowMonitorInterval(data.getWindowMonitorInterval())
                                       .countersEnabled(data.isCountersEnabled())
                                       .active(data.isActive()).build();
        }).collect(Collectors.toList());
    }

    public SMPPSessionConfigDto findById(int id) {
        log.info("Retrieving smpp session configuration by id = {}", id);

        return smppSessionConfigService.findById(id).map(data -> {
            return SMPPSessionConfigDto.builder()
                                       .id(data.getId())
                                       .name(data.getName())
                                       .windowSize(data.getWindowSize())
                                       .type(data.getType())
                                       .systemId(data.getSystemId())
                                       .password(data.getPassword())
                                       .systemType(data.getSystemType())
                                       .interfaceVersion(data.getInterfaceVersion())
                                       .addressRange(data.getAddressRange())
                                       .bindTimeout(data.getBindTimeout())
                                       .windowWaitTimeout(data.getWindowWaitTimeout())
                                       .requestExpiryTimeout(data.getRequestExpiryTimeout())
                                       .windowMonitorInterval(data.getWindowMonitorInterval())
                                       .countersEnabled(data.isCountersEnabled())
                                       .active(data.isActive()).build();
        }).orElseThrow(() -> new RuntimeException("No Content"));
    }

    public String deleteById(int id) {
        log.info("Delete smpp session configuration by id = {}", id);

        return smppSessionConfigService.deleteById(id);
    }

    @Transactional
    public int create(SMPPSessionConfigDtoInput newSMPPSessionConfigDtoInput) {
        SMPPSessionConfigDto response = smppSessionConfigService.create(newSMPPSessionConfigDtoInput);
        if(subscribers.get(response.getId()) != null){
            subscribers.get(response.getId()).next(response);
        }
        return response.getId();
    }

    @Transactional
    public int update(SMPPSessionConfigDtoInput updateSMPPSessionConfigDto, int id) {
        SMPPSessionConfigDto response = smppSessionConfigService.update(updateSMPPSessionConfigDto, id).get();
        if(subscribers.get(response.getId()) != null){
            subscribers.get(response.getId()).next(response);
        }
        return response.getId();
    }

    public Publisher<SMPPSessionConfigDto> onSMPPSessionConfigDtoUpdate(int id) {
        return Flux.create(subscriber -> subscribers.put(id,subscriber.onDispose(()->subscribers.remove(id, subscriber))), FluxSink.OverflowStrategy.LATEST);
    }
}
