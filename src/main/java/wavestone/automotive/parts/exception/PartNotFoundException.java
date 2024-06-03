package wavestone.automotive.parts.exception;

public class PartNotFoundException extends BusinessLogicException {
    public PartNotFoundException(String msg) {
        super(msg);
    }
}
