package com.example.rest.person.service.model;

import java.time.LocalDate;
import lombok.Data;

/**
 * Человек
 */
@Data
public class Person {

    /**
     * Идентификатор
     */
    private final Long id;
    /**
     * Имя
     */
    private final String firstName;
    /**
     * Фамилия
     */
    private final String lastName;
    /**
     * Отчество
     */
    private final String middleName;
    /**
     * Дата рождения
     */
    private final LocalDate birthday;
}

