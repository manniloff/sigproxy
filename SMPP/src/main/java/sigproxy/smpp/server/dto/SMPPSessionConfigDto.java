package sigproxy.smpp.server.dto;

import com.cloudhopper.smpp.SmppBindType;
import com.cloudhopper.smpp.type.Address;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
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
}
