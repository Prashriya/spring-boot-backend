package cl.blm.newmarketing.backend.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cl.blm.newmarketing.backend.dtos.PersonDto;
import cl.blm.newmarketing.backend.model.entities.Person;

/**
 *
 * @author Benjamin La Madrid <bg.lamadrid at gmail.com>
 */
@Component
public class PersonEntity2Dto
    implements Converter<Person, PersonDto> {
  @Override
  public PersonDto convert(Person source) {
    PersonDto target = new PersonDto();
    target.setPersonId(source.getId());
    target.setPersonFullName(source.getName());
    target.setPersonIdCard(source.getIdCard());

    String address = source.getAddress();
    if (address != null && !address.isEmpty()) {
      target.setPersonAddress(address);
    }

    String email = source.getEmail();
    if (email != null && !email.isEmpty()) {
      target.setPersonEmail(email);
    }

    return target;
  }
}