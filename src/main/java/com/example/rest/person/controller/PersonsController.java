package com.example.rest.person.controller;


import com.example.rest.common.exception.ApiNotFoundException;
import com.example.rest.person.controller.api.PersonsApi;
import com.example.rest.person.controller.dto.PersonCreationParametersDTO;
import com.example.rest.person.controller.dto.PersonCriteriaDTO;
import com.example.rest.person.controller.dto.PersonDTO;
import com.example.rest.person.service.PersonsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonsController implements PersonsApi {

    private final PersonsService service;
    private final PersonDtoModelMapper mapper;

    @Override
    public ResponseEntity<PersonDTO> create(PersonCreationParametersDTO personCreationParameter) {
        return ResponseEntity.ok(mapper.map(service.create(mapper.map(personCreationParameter))));
    }

    @Override
    public ResponseEntity<List<PersonDTO>> findByCriteria(PersonCriteriaDTO personCriteria) {
        return ResponseEntity.ok(mapper.map(service.findByCriteria(mapper.map(personCriteria))));
    }

    @Override
    public ResponseEntity<PersonDTO> findById(Long personId) {
        return ResponseEntity.ok(mapper.map(service.findById(personId)
                .orElseThrow(() -> new ApiNotFoundException(Message.PERSON_NOT_FOUND, personId))));
    }

}
