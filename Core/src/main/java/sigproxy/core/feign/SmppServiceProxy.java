package sigproxy.core.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "smpp-service", url="localhost:8004")
@RibbonClient(name="smpp-service")
public interface SmppServiceProxy {
    @GetMapping("/smpp")
    public String retrieveConnectionWithSmpp();
}
