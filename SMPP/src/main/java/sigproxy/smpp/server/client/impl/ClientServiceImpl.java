package sigproxy.smpp.server.client.impl;

import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.type.Address;
import com.cloudhopper.smpp.type.RecoverablePduException;
import com.cloudhopper.smpp.type.SmppChannelException;
import com.cloudhopper.smpp.type.SmppInvalidArgumentException;
import com.cloudhopper.smpp.type.SmppTimeoutException;
import com.cloudhopper.smpp.type.UnrecoverablePduException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sigproxy.smpp.server.client.Client;
import sigproxy.smpp.server.client.ClientService;
import sigproxy.smpp.server.service.SMPPSessionConfigDtoService;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final SMPPSessionConfigDtoService sessionConfigDtoService;
    @Override
    public long createClient() {
        log.info("Enter into creat Client method");
        DefaultSmppClient client = new DefaultSmppClient();

        SmppSessionConfiguration sessionConfig = new SmppSessionConfiguration();
        return  sessionConfigDtoService.findAll().stream().filter(cfg -> cfg.isActive()).map( cfg -> {
            log.info("starting client wiht configuration - {}", cfg.getName());
            sessionConfig.setType(cfg.getType());
            sessionConfig.setHost(cfg.getAddressRange().getAddress());
            sessionConfig.setPort(2777);
            sessionConfig.setSystemId(cfg.getSystemId());
            sessionConfig.setPassword(cfg.getPassword());

            try {
                SmppSession session = client.bind(sessionConfig);

                SubmitSm sm = createSubmitSm("Test", "79111234567", "Привет землянин!", "UCS-2");

                System.out.println("Try to send message");

                session.submit(sm, TimeUnit.SECONDS.toMillis(2));

                System.out.println("Message sent");

                System.out.println("Wait 10 seconds");

                TimeUnit.SECONDS.sleep(10);

                System.out.println("Destroy session");

                session.close();
                session.destroy();

                System.out.println("Destroy client");

                client.destroy();

                System.out.println("Bye!");
            } catch (SmppTimeoutException | SmppChannelException | UnrecoverablePduException | InterruptedException | RecoverablePduException ex) {
                log.error(Client.class.getName() + Level.SEVERE, null, ex);
            }
            return null;
        }).count();
    }

    @Override
    public SubmitSm createSubmitSm(String src, String dst, String text, String charset)
            throws SmppInvalidArgumentException {
        SubmitSm sm = new SubmitSm();

        // For alpha numeric will use
        // TON=5
        // NPI=0
        sm.setSourceAddress(new Address((byte)5, (byte)0, src));

        // For national numbers will use
        // TON=1
        // NPI=1
        sm.setDestAddress(new Address((byte)1, (byte)1, dst));

        // Set datacoding to UCS-2
        sm.setDataCoding((byte)8);

        // Encode text
        sm.setShortMessage(CharsetUtil.encode(text, charset));

        return sm;
    }
}
