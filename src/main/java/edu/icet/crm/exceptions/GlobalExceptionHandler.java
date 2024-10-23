package edu.icet.crm.exceptions;

import edu.icet.crm.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String FAILED_MSG = "FAILED";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse failed = ErrorResponse.builder().error(ex.getMessage()).status(FAILED_MSG).build();
        return ResponseEntity.ok().body(failed);
    }


    @ExceptionHandler(ZeroEntitiesException.class)
    public ResponseEntity<ErrorResponse> handleZeroEntitiesException(ZeroEntitiesException ex) {
        ErrorResponse failed = ErrorResponse.builder().error(ex.getMessage()).status(FAILED_MSG).build();
        return ResponseEntity.ok().body(failed);
    }

    @ExceptionHandler(InvalidParametersException.class)
    public ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParametersException ex) {
        ErrorResponse failed = ErrorResponse.builder().error(ex.getMessage()).status(FAILED_MSG).build();
        return ResponseEntity.ok().body(failed);
    }
}
