package org.maxkizi.shortlink.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maxkizi.shortlink.common.model.role.ApplicationUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "application_user")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser implements UserDetails {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "application_user_role_binding",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<ApplicationUserRole> roles;
    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired = true;
    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked = true;
    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired = true;
    @Column(name = "is_enabled")
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getId().getRoleType().getRoleName()))
                .collect(Collectors.toSet());
    }
}
