package com.example.rest.person.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Человек
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * Имя
     */
    private String firstName;
    /**
     * Фамилия
     */
    private String lastName;
    /**
     * Отчество
     */
    private String middleName;
    /**
     * Дата рождения
     */
    private LocalDate birthday;
}
