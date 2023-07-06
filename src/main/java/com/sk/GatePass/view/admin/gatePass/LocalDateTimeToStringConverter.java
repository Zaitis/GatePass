package com.sk.GatePass.view.admin.gatePass;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeToStringConverter implements Converter<LocalDateTime, String> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Result<String> convertToModel(LocalDateTime localDateTime, ValueContext context) {
        String formattedDateTime = localDateTime.format(FORMATTER);
        return Result.ok(formattedDateTime);
    }

    @Override
    public LocalDateTime convertToPresentation(String value, ValueContext context) {
        return LocalDateTime.parse(value, FORMATTER);
    }
}