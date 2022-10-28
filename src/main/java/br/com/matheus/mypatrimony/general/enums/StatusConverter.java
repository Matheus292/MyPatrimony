package br.com.matheus.mypatrimony.general.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Status attribute) {
        return Objects.isNull(attribute) ? Status.INACTIVE.status() : attribute.status();
    }

    @Override
    public Status convertToEntityAttribute(Integer dbData) {
        return Objects.isNull(dbData) ? Status.INACTIVE : Status.getStatusById(dbData);
    }
}
