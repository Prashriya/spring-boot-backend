package org.trebol.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.trebol.exceptions.BadInputException;
import org.trebol.exceptions.EntityAlreadyExistsException;

import javassist.NotFoundException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Catches declared exceptions at controller level and submits custom responses.
 * @author Benjamin La Madrid <bg.lamadrid at gmail.com>
 */
@RestControllerAdvice
public class ExceptionsControllerAdvice {

  private final Logger logger = LoggerFactory.getLogger(ExceptionsControllerAdvice.class);

  public ExceptionsControllerAdvice() { }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public String handleException(NotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(EntityAlreadyExistsException.class)
  public String handleException(EntityAlreadyExistsException ex) {
    return ex.getMessage();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadInputException.class)
  public String handleException(BadInputException ex) {
    return ex.getMessage();
  }

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler(RuntimeException.class)
  public String handleException(RuntimeException ex) {
    logger.warn("Runtime exception caught: ", ex);
    return ex.getMessage();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }
}
