package sigproxy.smpp.dto;

import graphql.schema.GraphQLInputType;

public class AddressInput extends com.cloudhopper.smpp.type.Address  implements GraphQLInputType {
    @Override
    public String getName() {
        return "Address input type";
    }
}
