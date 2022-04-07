package org.maxkizi.shortlink.common.converter;

import org.maxkizi.shortlink.common.model.role.RoleType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleTypeConverter implements AttributeConverter<RoleType, String> {
    @Override
    public String convertToDatabaseColumn(RoleType roleType) {
        return roleType.name();
    }

    @Override
    public RoleType convertToEntityAttribute(String roleName) {
        return RoleType.getRoleTypeByName(roleName);
    }
}
