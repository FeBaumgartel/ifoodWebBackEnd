package com.ifoodWebBackEnd.domain.user;

import com.ifoodWebBackEnd.dtos.LoginRequestDTO;
import com.ifoodWebBackEnd.dtos.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@Table(name = "users")
@Entity()
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@Where(clause = "deleted='false'")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @JoinColumn(name = "user_id")
    private User user;
    @CreationTimestamp
    private Instant creationTimestamp;
    @UpdateTimestamp
    private Instant updateTimestamp;
    private boolean deleted = false;

    public boolean isLoginCorrect(LoginRequestDTO loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }

    public User(UserRequestDTO data, String encodedPassword, Role role, User updateUser){
        this.name = data.name();
        this.username = data.username();
        this.password = encodedPassword;
        this.role = role;
        this.user = updateUser;
    }
}
