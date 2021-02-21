package sigproxy.core.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "http-service")
@RibbonClient(name="http-service")
public interface HTTPServiceProxy {
    @GetMapping("/http")
    public String retrieveConnectionWithHttp();
}
