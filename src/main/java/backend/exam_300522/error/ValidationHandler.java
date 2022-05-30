package backend.exam_300522.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {
    Map<String, Object> errorBody = new HashMap<>();
    errorBody.put("path", ((ServletWebRequest)request).getRequest().getRequestURI());
    errorBody.put("status","400");
    List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(err-> err.getField()+": "+err.getDefaultMessage())
            .collect(Collectors.toList());
    errorBody.put("timestamp",new Date());
    errorBody.put("error", "Validation Error");
    errorBody.put("message", String.join(",",errors));
    return new ResponseEntity<Object>(errorBody, HttpStatus.BAD_REQUEST);
  }
}

