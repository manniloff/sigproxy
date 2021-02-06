package sigproxy.smpp.server.exception;

public class NoDataException extends  Exception {
    public NoDataException (String errorMessage) {
        super(errorMessage);
    }
}
