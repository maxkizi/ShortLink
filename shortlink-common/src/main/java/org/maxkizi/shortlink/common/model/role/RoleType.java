package org.maxkizi.shortlink.common.model.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum RoleType {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_GUEST("ROLE_GUEST");

    private String roleName;

    public static RoleType getRoleTypeByName(String roleName) {
        return Arrays.stream(values())
                .filter(role -> roleName.equals(role.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Нет роли с таким именем"));
    }
}
