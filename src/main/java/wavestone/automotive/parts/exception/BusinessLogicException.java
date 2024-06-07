package wavestone.automotive.parts.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessLogicException extends RuntimeException {


    private final HttpStatus status;

    public BusinessLogicException(String msg, HttpStatus status) {
        super(msg);
        this.status = status != null ? status : HttpStatus.BAD_REQUEST;
    }

    public BusinessLogicException(String msg) {
        super(msg);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
