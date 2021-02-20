package sigproxy.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sigproxy.core.feign.HTTPServiceProxy;
import sigproxy.core.feign.PublisherServiceProxy;
import sigproxy.core.feign.SIPServiceProxy;
import sigproxy.core.feign.SigtranServiceProxy;
import sigproxy.core.feign.SmppServiceProxy;
import sigproxy.core.feign.SmtpServiceProxy;

@Controller
@RequestMapping("/core")
@RequiredArgsConstructor
public class CoreController {
    private final HTTPServiceProxy httpServiceProxy;
    private final PublisherServiceProxy publisherServiceProxy;
    private final SigtranServiceProxy sigtranServiceProxy;
    private final SIPServiceProxy sipServiceProxy;
    private final SmppServiceProxy smppServiceProxy;
    private final SmtpServiceProxy smtpServiceProxy;

    @GetMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> testConnection() {
        return ResponseEntity.ok("Connected with Core");
    }

    @GetMapping(value = "/connections/http", produces = "application/json")
    ResponseEntity<?> getHttpConnection() {
        return ResponseEntity.ok(httpServiceProxy.retrieveConnectionWithHttp());
    }

    @GetMapping(value = "/connections/publisher", produces = "application/json")
    ResponseEntity<?> getPublisherConnection() {
        return ResponseEntity.ok(publisherServiceProxy.retrieveConnectionWithPublisher());
    }

    @GetMapping(value = "/connections/sigtran", produces = "application/json")
    ResponseEntity<?> getSigtranConnection() {
        return ResponseEntity.ok(sigtranServiceProxy.retrieveConnectionWithSigtran());
    }

    @GetMapping(value = "/connections/sip", produces = "application/json")
    ResponseEntity<?> getSipConnection() {
        return ResponseEntity.ok(sipServiceProxy.retrieveConnectionWithSip());
    }

    @GetMapping(value = "/connections/smpp", produces = "application/json")
    ResponseEntity<?> getSmppConnection() {
        return ResponseEntity.ok(smppServiceProxy.retrieveConnectionWithSmpp());
    }

    @GetMapping(value = "/connections/smtp", produces = "application/json")
    ResponseEntity<?> getSmtpConnection() {
        return ResponseEntity.ok(smtpServiceProxy.retrieveConnectionWithSmtp());
    }
}
