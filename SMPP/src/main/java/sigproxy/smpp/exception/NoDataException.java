package sigproxy.smpp.exception;

public class NoDataException extends  Exception {
    public NoDataException (String errorMessage) {
        super(errorMessage);
    }
}
