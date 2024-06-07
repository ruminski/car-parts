package wavestone.automotive.parts.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternalServerException extends RuntimeException {


    private final HttpStatus status;

    public InternalServerException(String msg) {
        super(msg);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
