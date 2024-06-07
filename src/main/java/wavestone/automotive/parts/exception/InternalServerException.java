package wavestone.automotive.parts.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when no recovery is possible
 */
@Getter
public class InternalServerException extends RuntimeException {


    private final HttpStatus status;

    public InternalServerException(String msg) {
        super(msg);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
