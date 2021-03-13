package sigproxy.smpp.server.server;

import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.pdu.DeliverSm;
import com.cloudhopper.smpp.pdu.PduResponse;
import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.pdu.SubmitSmResp;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmppSessionHandlerImpl extends AbstractSmppSessionHandler{
    @Override
    protected PduResponse fireDeliverSmRequest(DeliverSm pduRequest) {
        return null;
    }

    @Override
    protected PduResponse fireSubmitSmRequest(SubmitSm pduRequest) {
        SubmitSmResp response = pduRequest.createResponse();
        //TODO decode message by received data coding scheme
        log.info("Received submitSm message from: {} to: {} text: {}", pduRequest.getSourceAddress(), pduRequest.getDestAddress(),
                new String(pduRequest.getShortMessage()));
        //TODO send msg to kafka and accumulate all parts msg
        response.setCommandStatus(SmppConstants.STATE_ACCEPTED);
        return  response;
    }
}
