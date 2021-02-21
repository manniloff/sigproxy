package sigproxy.core.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "sigtran-service")
@RibbonClient(name="sigtran-service")
public interface SigtranServiceProxy {
    @GetMapping("/sigtran")
    public String retrieveConnectionWithSigtran();
}
