package cl.blm.newmarketing.backend.api.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.blm.newmarketing.backend.CustomProperties;
import cl.blm.newmarketing.backend.api.DataServiceClient;
import cl.blm.newmarketing.backend.api.GenericDataController;
import cl.blm.newmarketing.backend.api.pojo.SellerPojo;
import cl.blm.newmarketing.backend.model.entities.Seller;
import cl.blm.newmarketing.backend.services.data.GenericDataService;

/**
 * API point of entry for Seller entities
 *
 * @author Benjamin La Madrid <bg.lamadrid at gmail.com>
 */
@RestController
@RequestMapping("/api")
public class SellersDataController
    extends DataServiceClient<SellerPojo, Seller, Integer>
    implements GenericDataController<SellerPojo, Integer> {
  private final static Logger LOG = LoggerFactory.getLogger(SellersDataController.class);

  @Autowired
  public SellersDataController(CustomProperties globals, GenericDataService<SellerPojo, Seller, Integer> crudService) {
    super(globals, crudService);
  }

  @PostMapping("/seller")
  public SellerPojo create(@RequestBody @Valid SellerPojo input) {
    LOG.info("create");
    SellerPojo result = crudService.create(input);
    return result;
  }

  @GetMapping("/seller/{id}")
  public SellerPojo readOne(@PathVariable Integer id) {
    LOG.info("read");
    SellerPojo found = crudService.find(id);
    return found;
  }

  @GetMapping("/sellers")
  public Collection<SellerPojo> readMany(@RequestParam Map<String, String> allRequestParams) {
    return this.readMany(null, null, allRequestParams);
  }

  @GetMapping("/sellers/{requestPageSize}")
  public Collection<SellerPojo> readMany(@PathVariable Integer requestPageSize,
      @RequestParam Map<String, String> allRequestParams) {
    return this.readMany(requestPageSize, null, allRequestParams);
  }

  @GetMapping("/sellers/{requestPageSize}/{requestPageIndex}")
  public Collection<SellerPojo> readMany(@PathVariable Integer requestPageSize, @PathVariable Integer requestPageIndex,
      @RequestParam Map<String, String> allRequestParams) {
    LOG.info("read");
    Collection<SellerPojo> clients = this.readFromService(requestPageSize, requestPageIndex, allRequestParams);
    LOG.info("{}", clients);
    return clients;
  }

  @PutMapping("/seller")
  public SellerPojo update(@RequestBody @Valid SellerPojo input) {
    LOG.info("update");
    SellerPojo processed = crudService.update(input, input.id);
    return processed;
  }

  @PutMapping("/seller/{id}")
  public SellerPojo update(@RequestBody @Valid SellerPojo input, @PathVariable Integer id) {
    LOG.info("update");
    SellerPojo processed = crudService.update(input, id);
    return processed;
  }

  @DeleteMapping("/seller/{id}")
  public boolean delete(@PathVariable Integer id) {
    LOG.info("delete");
    boolean result = crudService.delete(id);
    return result;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }
}