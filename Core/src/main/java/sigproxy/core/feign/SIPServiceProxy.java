package sigproxy.core.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "sip-service")
@RibbonClient(name="sip-service")
public interface SIPServiceProxy {
    @GetMapping("/sip")
    public String retrieveConnectionWithSip();
}
