package com.example.rest.person.service;

import com.example.rest.person.entity.PersonEntity;
import com.example.rest.person.repository.PersonsRepository;
import com.example.rest.person.service.model.Person;
import com.example.rest.person.service.model.PersonCreationParameters;
import com.example.rest.person.service.model.PersonCriteria;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonsServiceImplTest {

    private static final long PERSON_ID = 1;
    private static final String FIRST_NAME = "FIRST_NAME";
    private static final String LAST_NAME = "LAST_NAME";
    private static final String MIDDLE_NAME = "MIDDLE_NAME";
    private static final LocalDate BIRTHDAY = LocalDate.now();

    @Mock
    private PersonsRepository repository;
    @Spy
    private final PersonsModelEntityMapper mapper = Mappers.getMapper(PersonsModelEntityMapper.class);
    @InjectMocks
    private PersonsServiceImpl service;

    @Test
    void create() {
        PersonCreationParameters creationParameters =
                new PersonCreationParameters(FIRST_NAME, LAST_NAME, MIDDLE_NAME, BIRTHDAY);
        PersonEntity personEntity = mapper.map(creationParameters);
        personEntity.setId(PERSON_ID);
        when(repository.save(eq(mapper.map(creationParameters)))).thenReturn(personEntity);

        Person actual = service.create(creationParameters);

        assertEquals(getPerson(), actual);
    }

    @Test
    void findByCriteria() {
        PersonCriteria personCriteria = new PersonCriteria(LocalDate.now(), LocalDate.now());
        PersonEntity personEntity = getPersonEntity();
        when(repository.findAllByBirthdayBetween(personCriteria.getBirthdayStart(), personCriteria.getBirthdayEnd()))
                .thenReturn(Collections.singletonList(personEntity));

        List<Person> actual = service.findByCriteria(personCriteria);

        assertEquals(Collections.singletonList(getPerson()), actual);
    }

    private PersonEntity getPersonEntity() {
        return new PersonEntity(PERSON_ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, BIRTHDAY);
    }

    private Person getPerson() {
        return new Person(PERSON_ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, BIRTHDAY);
    }

    @Test
    void findById() {
        when(repository.findById(PERSON_ID)).thenReturn(Optional.of(getPersonEntity()));

        Optional<Person> actual = service.findById(PERSON_ID);

        assertEquals(Optional.of(getPerson()), actual);
    }

    @Test
    void findById_Empty() {
        when(repository.findById(PERSON_ID)).thenReturn(Optional.empty());

        Optional<Person> actual = service.findById(PERSON_ID);

        assertEquals(Optional.empty(), actual);
    }
}
