package com.example.rest.person.controller;


import com.example.rest.common.handling.MessageTemplate;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Message implements MessageTemplate {

    PERSON_NOT_FOUND("Человек с идентификатором {0} не найден.");

    private final String defaultMessage;

    @Override
    public String getText(Object... args) {
        return new MessageFormat(defaultMessage).format(args);
    }
}
