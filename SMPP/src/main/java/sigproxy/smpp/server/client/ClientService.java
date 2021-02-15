package sigproxy.smpp.server.client;

import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.type.SmppInvalidArgumentException;

public interface ClientService {
    long createClient();

    SubmitSm createSubmitSm(String src, String dst, String text, String charset) throws SmppInvalidArgumentException;
}
