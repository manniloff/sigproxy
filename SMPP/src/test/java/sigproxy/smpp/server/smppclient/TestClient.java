package sigproxy.smpp.server.smppclient;

import com.cloudhopper.smpp.SmppBindType;
import com.cloudhopper.smpp.SmppServerHandler;
import com.cloudhopper.smpp.SmppServerSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import com.cloudhopper.smpp.pdu.BaseBind;
import com.cloudhopper.smpp.pdu.BaseBindResp;
import com.cloudhopper.smpp.type.SmppProcessingException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

@Slf4j
public class TestClient {
    static public final int SESSION_COUNT = 10;

    public static final SmppServerHandler SERVER_HANDLER = new SmppServerHandler() {
        public void sessionBindRequested(Long aLong, SmppSessionConfiguration smppSessionConfiguration,
                                         BaseBind baseBind) throws SmppProcessingException {
            log.info("sessionBindRequested");
        }

        public void sessionCreated(Long aLong, SmppServerSession smppServerSession, BaseBindResp baseBindResp)
                throws SmppProcessingException {
            log.info("sessionCreated");
        }

        public void sessionDestroyed(Long aLong, SmppServerSession smppServerSession) {
            log.info("sessionDestroyed");
        }
    };

    @SneakyThrows
    public static void main(String[] args) {
        DefaultSmppClient clientBootstrap = new DefaultSmppClient(Executors.newCachedThreadPool(), SESSION_COUNT);

        SmppSessionConfiguration config = new SmppSessionConfiguration();
        config.setWindowSize(500);
        config.setName("Tester.Session.0");
        config.setType(SmppBindType.TRANSCEIVER);
        config.setHost("127.0.0.1");
        config.setPort(2777);
        config.setConnectTimeout(10000);
        config.setSystemId("1234567890");
        config.setPassword("password");
        config.getLoggingOptions().setLogBytes(false);
        // to enable monitoring (request expiration)
        config.setRequestExpiryTimeout(30000);
        config.setWindowMonitorInterval(15000);
        config.setCountersEnabled(true);


        log.info("Starting SMPP server...");
        CountDownLatch allSessionsBoundSignal = new CountDownLatch(SESSION_COUNT);
        CountDownLatch startSendingSignal = new CountDownLatch(1);

        clientBootstrap.bind(config);
        log.info("SMPP server started");
        //ssl
        /*SslConfiguration sslConfig = new SslConfiguration();
        sslConfig.setKeyStorePath("src/test/resources/keystore");
        sslConfig.setKeyStorePassword("changeit");
        sslConfig.setKeyManagerPassword("changeit");
        sslConfig.setTrustStorePath("src/test/resources/keystore");
        sslConfig.setTrustStorePassword("changeit");
        configuration.setUseSsl(true);
        configuration.setSslConfiguration(sslConfig);*/
    }
}
