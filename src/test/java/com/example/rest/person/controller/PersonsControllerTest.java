package com.example.rest.person.controller;

import com.example.rest.common.exception.ApiNotFoundException;
import com.example.rest.person.controller.PersonsControllerTest.TestConfiguration;
import com.example.rest.person.controller.dto.PersonCreationParametersDTO;
import com.example.rest.person.controller.dto.PersonCriteriaDTO;
import com.example.rest.person.service.PersonsService;
import com.example.rest.person.service.model.Person;
import com.example.rest.person.service.model.PersonCreationParameters;
import com.example.rest.person.service.model.PersonCriteria;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonsController.class)
@ContextConfiguration(classes = {PersonsController.class, TestConfiguration.class})
class PersonsControllerTest {

    private static final String URL_BASE = "/persons";
    private static final String URL_SEARCH_BY_CRITERIA = URL_BASE + "/search";
    private static final String URL_SEARCH_BY_ID = URL_BASE + "/{personId}";

    private static final long PERSON_ID = 1;
    private static final String FIRST_NAME = "FIRST_NAME";
    private static final String LAST_NAME = "LAST_NAME";
    private static final String MIDDLE_NAME = "MIDDLE_NAME";
    private static final LocalDate BIRTHDAY = LocalDate.now();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PersonsService service;

    @Test
    void create() throws Exception {
        when(service.create(any(PersonCreationParameters.class))).thenReturn(getPerson());

        mockMvc.perform(post(URL_BASE).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(getPersonCreationParameterDTO())))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(getPerson())));
    }

    private Person getPerson() {
        return new Person(PERSON_ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, BIRTHDAY);
    }

    private PersonCreationParametersDTO getPersonCreationParameterDTO() {
        return new PersonCreationParametersDTO()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .middleName(MIDDLE_NAME)
                .birthday(BIRTHDAY);
    }

    @Test
    void findByCriteria() throws Exception {
        when(service.findByCriteria(any(PersonCriteria.class))).thenReturn(Collections.singletonList(getPerson()));

        mockMvc.perform(post(URL_SEARCH_BY_CRITERIA).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(getPersonCriteriaDTO())))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(Collections.singletonList(getPerson()))));
    }

    private PersonCriteriaDTO getPersonCriteriaDTO() {
        return new PersonCriteriaDTO().birthdayStart(BIRTHDAY).birthdayEnd(BIRTHDAY);
    }

    @Test
    void findById() throws Exception {
        when(service.findById(PERSON_ID)).thenReturn(Optional.of(getPerson()));

        mockMvc.perform(get(URL_SEARCH_BY_ID, PERSON_ID))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(getPerson())));
    }

    @Test
    void findById_NotFound() {
        when(service.findById(PERSON_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> mockMvc.perform(get(URL_SEARCH_BY_ID, PERSON_ID)))
                .hasCauseInstanceOf(ApiNotFoundException.class);
    }

    @Configuration
    public static class TestConfiguration {

        @Bean
        public PersonDtoModelMapper mapper() {
            return Mappers.getMapper(PersonDtoModelMapper.class);
        }

        @Bean
        public PersonsService service() {
            return Mockito.mock(PersonsService.class);
        }
    }
}
