package nl.codebase.faceter.forms;

import nl.codebase.faceter.forms.definition.model.FormDefinitionNotFoundException;
import nl.codebase.faceter.forms.definition.model.FormsServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FormsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { FormsServiceException.class })
    protected ResponseEntity<Object> handleFormsServiceException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = { FormDefinitionNotFoundException.class })
    protected ResponseEntity<Object> handleFormDefinitionNotFoundException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


}

