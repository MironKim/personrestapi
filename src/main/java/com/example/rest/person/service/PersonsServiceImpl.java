package com.example.rest.person.service;

import com.example.rest.person.repository.PersonsRepository;
import com.example.rest.person.service.model.Person;
import com.example.rest.person.service.model.PersonCreationParameters;
import com.example.rest.person.service.model.PersonCriteria;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonsServiceImpl implements PersonsService {

    private final PersonsRepository repository;
    private final PersonsModelEntityMapper mapper;

    @Override
    public Person create(PersonCreationParameters creationParameter) {
        return mapper.map(repository.save(mapper.map(creationParameter)));
    }

    @Override
    public List<Person> findByCriteria(PersonCriteria personCriteria) {
        return mapper.map(repository
                .findAllByBirthdayBetween(personCriteria.getBirthdayStart(), personCriteria.getBirthdayEnd()));
    }

    @Override
    public Optional<Person> findById(long personId) {
        return repository.findById(personId).map(mapper::map);
    }
}
