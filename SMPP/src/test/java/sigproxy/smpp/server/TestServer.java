package sigproxy.smpp.server;

import com.cloudhopper.smpp.SmppServerConfiguration;
import com.cloudhopper.smpp.SmppServerHandler;
import com.cloudhopper.smpp.SmppServerSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.SmppSessionHandler;
import com.cloudhopper.smpp.impl.DefaultSmppServer;
import com.cloudhopper.smpp.pdu.BaseBind;
import com.cloudhopper.smpp.pdu.BaseBindResp;
import com.cloudhopper.smpp.pdu.BindReceiverResp;
import com.cloudhopper.smpp.pdu.BindTransceiverResp;
import com.cloudhopper.smpp.pdu.PduResponse;
import com.cloudhopper.smpp.type.RecoverablePduException;
import com.cloudhopper.smpp.type.SmppChannelException;
import com.cloudhopper.smpp.type.SmppProcessingException;
import com.cloudhopper.smpp.type.UnrecoverablePduException;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestServer {

    public static final SmppServerHandler SERVER_HANDLER = new SmppServerHandler() {
        public void sessionBindRequested(Long aLong, SmppSessionConfiguration smppSessionConfiguration,
                                         BaseBind baseBind) throws SmppProcessingException {
            log.info("sessionBindRequested");

        }

        public void sessionCreated(Long aLong, SmppServerSession smppServerSession, BaseBindResp baseBindResp)
                throws SmppProcessingException {
            log.info("sessionCreated");
            try {
                BindTransceiverResp pduResponse = new BindTransceiverResp();
                pduResponse.setCommandStatus(2);
                smppServerSession.sendResponsePdu(pduResponse);
            } catch (RecoverablePduException e) {
                e.printStackTrace();
            } catch (UnrecoverablePduException e) {
                e.printStackTrace();
            } catch (SmppChannelException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void sessionDestroyed(Long aLong, SmppServerSession smppServerSession) {
            log.info("sessionDestroyed");
        }
    };

    @SneakyThrows
    public static void main(String[] args) {
        SmppServerConfiguration configuration = new SmppServerConfiguration();
        configuration.setPort(2777);
        configuration.setMaxConnectionSize(10);
        configuration.setNonBlockingSocketsEnabled(true);
        configuration.setDefaultRequestExpiryTimeout(30000);
        configuration.setDefaultWindowMonitorInterval(15000);
        configuration.setDefaultWindowSize(5);
        configuration.setDefaultWindowWaitTimeout(configuration.getDefaultRequestExpiryTimeout());
        configuration.setDefaultSessionCountersEnabled(true);
        configuration.setJmxEnabled(true);

        DefaultSmppServer smppServer = new DefaultSmppServer(configuration, SERVER_HANDLER);

        log.info("Starting SMPP server...");

        smppServer.start();
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
