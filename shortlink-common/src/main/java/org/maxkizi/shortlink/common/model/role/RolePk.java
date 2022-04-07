package org.maxkizi.shortlink.common.model.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.maxkizi.shortlink.common.converter.RoleTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePk implements Serializable {
    @Column(name = "id")
    @Convert(converter = RoleTypeConverter.class)
    private RoleType roleType;
}
