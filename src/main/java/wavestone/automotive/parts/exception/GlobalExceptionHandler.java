package wavestone.automotive.parts.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
@SuppressWarnings("unused")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BusinessLogicException.class})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Client error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})})
    protected ResponseEntity<Object> handleClientError(BusinessLogicException ex, WebRequest request) {
        log.error("Business exception occurred: ", ex);
        return handleExceptionInternal(ex, new ErrorResponse(ex.getMessage()), new HttpHeaders(), ex.getStatus(), request);
    }

    @ExceptionHandler(Exception.class)
    @ApiResponses(value = {@ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})})
    protected ResponseEntity<Object> handleServerError(Exception ex, WebRequest request) {
        log.error("Exception occurred: ", ex);
        return handleExceptionInternal(ex, new ErrorResponse("Internal Server Error"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
