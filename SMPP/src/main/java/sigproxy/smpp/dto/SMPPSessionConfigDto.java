package sigproxy.smpp.dto;

import com.cloudhopper.smpp.SmppBindType;
import com.cloudhopper.smpp.type.Address;
import graphql.schema.GraphQLInputType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;

@Value
@Builder
@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SMPPSessionConfigDto {

    private int id;
    private String name;
    private int windowSize;
    private SmppBindType type;
    private String systemId;
    private String password;
    private String systemType;
    private byte interfaceVersion;
    private Address addressRange;
    private long bindTimeout;
    private long windowWaitTimeout;
    private long requestExpiryTimeout;
    private long windowMonitorInterval;
    private boolean countersEnabled;
    private boolean active;
}
