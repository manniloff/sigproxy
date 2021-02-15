package sigproxy.smpp.server.dto;

import com.cloudhopper.smpp.SmppBindType;
import graphql.schema.GraphQLInputType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SMPPSessionConfigDtoInput  implements GraphQLInputType {
    @Override
    public String getName() {
        return "SMPPSessionConfig Input type";
    }

    private int id;
    private String name;
    private int windowSize;
    private SmppBindType type;
    private String systemId;
    private String password;
    private String systemType;
    private byte interfaceVersion;
    private AddressInput addressRange;
    private long bindTimeout;
    private long windowWaitTimeout;
    private long requestExpiryTimeout;
    private long windowMonitorInterval;
    private boolean countersEnabled;
    private boolean active;
}
