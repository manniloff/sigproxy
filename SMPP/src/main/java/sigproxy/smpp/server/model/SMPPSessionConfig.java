package sigproxy.smpp.server.model;

import com.cloudhopper.smpp.SmppSessionConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SMPPSessionConfig {
    @Id
    @EqualsAndHashCode.Include
    private int id;

    private SmppSessionConfiguration config;
}
