package com.ifoodWebBackEnd.domain.user;

import com.ifoodWebBackEnd.dtos.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Role role;

    public User(UserRequestDTO data, String password){
        this.name = data.name();
        this.email = data.email();
        this.password = password;
        this.role = data.role();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Role.RESTAURANT) return List.of(new SimpleGrantedAuthority("ROLE_RESTAURANT"));
        else return List.of(new SimpleGrantedAuthority("ROLE_COSTUMER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
