package sigproxy.core.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "smtp-service")
@RibbonClient(name="smtp-service")
public interface SmtpServiceProxy {
    @GetMapping("/smtp")
    public String retrieveConnectionWithSmtp();
}
