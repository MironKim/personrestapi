package com.example.rest.person.service;

import com.example.rest.person.entity.PersonEntity;
import com.example.rest.person.service.model.Person;
import com.example.rest.person.service.model.PersonCreationParameters;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonsModelEntityMapper {

    List<Person> map(List<PersonEntity> source);

    Person map(PersonEntity source);

    PersonEntity map(PersonCreationParameters source);
}
