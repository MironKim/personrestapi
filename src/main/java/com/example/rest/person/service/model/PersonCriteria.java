package com.example.rest.person.service.model;

import java.time.LocalDate;
import lombok.Data;

/**
 * Критерии поиска человека
 */
@Data
public class PersonCriteria {

    /**
     * Минимальная дата рождения
     */
    private final LocalDate birthdayStart;

    /**
     * Максимальная дата рождения
     */
    private final LocalDate birthdayEnd;

}

