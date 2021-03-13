package sigproxy.smpp.server.server;

import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
import com.cloudhopper.smpp.pdu.DeliverSm;
import com.cloudhopper.smpp.pdu.PduRequest;
import com.cloudhopper.smpp.pdu.PduResponse;
import com.cloudhopper.smpp.pdu.SubmitSm;

public abstract class AbstractSmppSessionHandler extends DefaultSmppSessionHandler {
    @Override
    public PduResponse firePduRequestReceived(PduRequest pduRequest) {
       return switch (pduRequest.getCommandId()) {
            case SmppConstants.CMD_ID_SUBMIT_SM -> fireSubmitSmRequest((SubmitSm) pduRequest);
            case SmppConstants.CMD_ID_DELIVER_SM -> fireDeliverSmRequest((DeliverSm) pduRequest);
            default -> throw new RuntimeException("Not supported commandId");
        };
    }

    protected abstract PduResponse fireDeliverSmRequest(DeliverSm pduRequest);

    protected abstract PduResponse fireSubmitSmRequest(SubmitSm pduRequest);
}
