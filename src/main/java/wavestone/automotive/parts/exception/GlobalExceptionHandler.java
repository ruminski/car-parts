package wavestone.automotive.parts.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
@SuppressWarnings("unused")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BusinessLogicException.class})
    protected ResponseEntity<Object> handleClientError(BusinessLogicException ex, WebRequest request) {
        log.error("Business exception occurred: ", ex);
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), ex.getStatus(), request);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleServerError(Exception ex, WebRequest request) {
        log.error("Exception occurred: ", ex);
        return handleExceptionInternal(ex, "Internal Server Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
