package com.example.rest.person.repository;

import com.example.rest.person.entity.PersonEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PersonsRepository extends CrudRepository<PersonEntity, Long> {

    List<PersonEntity> findAllByBirthdayBetween(LocalDate birthDayStart, LocalDate birthDayEnd);

}
