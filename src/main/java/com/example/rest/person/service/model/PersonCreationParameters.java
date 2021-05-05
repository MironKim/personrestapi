package com.example.rest.person.service.model;

import java.time.LocalDate;
import lombok.Data;

/**
 * Параметры для создания человека
 */
@Data
public class PersonCreationParameters {

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

