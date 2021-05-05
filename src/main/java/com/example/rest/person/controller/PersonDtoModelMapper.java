package com.example.rest.person.controller;


import com.example.rest.person.controller.dto.PersonCreationParametersDTO;
import com.example.rest.person.controller.dto.PersonCriteriaDTO;
import com.example.rest.person.controller.dto.PersonDTO;
import com.example.rest.person.service.model.Person;
import com.example.rest.person.service.model.PersonCreationParameters;
import com.example.rest.person.service.model.PersonCriteria;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDtoModelMapper {

    PersonCreationParameters map(PersonCreationParametersDTO source);

    PersonCriteria map(PersonCriteriaDTO source);

    PersonDTO map(Person source);

    List<PersonDTO> map(List<Person> source);
}
