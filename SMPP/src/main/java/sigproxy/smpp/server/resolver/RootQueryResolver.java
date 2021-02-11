package sigproxy.smpp.server.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class RootQueryResolver implements GraphQLQueryResolver {

    public String getHello() {
        return "Hello World!";
    }
}
