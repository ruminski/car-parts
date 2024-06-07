package wavestone.automotive.parts.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessLogicException {
    public ResourceNotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }
}
