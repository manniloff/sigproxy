package sigproxy.core.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "publisher-service", url="localhost:8002")
@RibbonClient(name="publisher-service")
public interface PublisherServiceProxy {
    @GetMapping("/publisher")
    public String retrieveConnectionWithPublisher();
}
