package com.example.rest.person.service;

import com.example.rest.person.service.model.Person;
import com.example.rest.person.service.model.PersonCreationParameters;
import com.example.rest.person.service.model.PersonCriteria;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface PersonsService {

    /**
     * Создает человека с заданными параметрами
     *
     * @param creationParameter параметры
     * @return созданного человека
     */
    Person create(PersonCreationParameters creationParameter);

    /**
     * Поиск людей по параметрам
     *
     * @param personCriteria параметры поиска
     * @return список людей
     */
    List<Person> findByCriteria(PersonCriteria personCriteria);

    /**
     * Поиск человека по идентификатору
     *
     * @param personId идентификатор
     * @return человека если найден
     */
    Optional<Person> findById(long personId);
}
