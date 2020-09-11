package cl.blm.newmarketing.store.api.controllers;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import cl.blm.newmarketing.store.CustomProperties;
import cl.blm.newmarketing.store.api.GenericEntityDataController;
import cl.blm.newmarketing.store.api.pojo.ProductTypePojo;
import cl.blm.newmarketing.store.jpa.entities.ProductType;
import cl.blm.newmarketing.store.services.crud.GenericEntityCrudService;

/**
 * API point of entry for ProductType entities
 *
 * @author Benjamin La Madrid <bg.lamadrid at gmail.com>
 */
@RestController
@RequestMapping("/api")
public class ProductTypesDataController
    extends GenericEntityDataController<ProductTypePojo, ProductType, Integer> {

  @Autowired
  public ProductTypesDataController(CustomProperties globals,
      GenericEntityCrudService<ProductTypePojo, ProductType, Integer> crudService) {
    super(globals, crudService);
  }

  @Override
  @PostMapping("/product_type")
  public Integer create(@RequestBody @Valid ProductTypePojo input) {
    return super.create(input);
  }

  @Override
  @GetMapping("/product_type/{id}")
  public ProductTypePojo readOne(@PathVariable Integer id) {
    return super.readOne(id);
  }

  @GetMapping("/product_types")
  public Collection<ProductTypePojo> readMany(@RequestParam Map<String, String> allRequestParams) {
    return super.readMany(null, null, allRequestParams);
  }

  @GetMapping("/product_types/{requestPageSize}")
  public Collection<ProductTypePojo> readMany(@PathVariable Integer requestPageSize,
      @RequestParam Map<String, String> allRequestParams) {
    return super.readMany(requestPageSize, null, allRequestParams);
  }

  @Override
  @GetMapping("/product_types/{requestPageSize}/{requestPageIndex}")
  public Collection<ProductTypePojo> readMany(@PathVariable Integer requestPageSize,
      @PathVariable Integer requestPageIndex, @RequestParam Map<String, String> allRequestParams) {
    return super.readMany(requestPageSize, requestPageIndex, allRequestParams);
  }

  @PutMapping("/product_type")
  public Integer update(@RequestBody @Valid ProductTypePojo input) {
    return super.update(input, input.getId());
  }

  @Override
  @PutMapping("/product_type/{id}")
  public Integer update(@RequestBody @Valid ProductTypePojo input, @PathVariable Integer id) {
    return super.update(input, id);
  }

  @Override
  @DeleteMapping("/product_type/{id}")
  public boolean delete(@PathVariable Integer id) {
    return super.delete(id);
  }

  @Override
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    return super.handleValidationExceptions(ex);
  }
}