package br.com.matheus.mypatrimony.auth.repository;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class LoginTypeConverter implements AttributeConverter<LoginType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(LoginType attribute) {
        return Objects.isNull(attribute) ? -1 : attribute.type();
    }

    @Override
    public LoginType convertToEntityAttribute(Integer dbData) {
        return  Objects.isNull(dbData) ? LoginType.UNKNOWN : LoginType.getLoginTypeById(dbData);
    }
}
