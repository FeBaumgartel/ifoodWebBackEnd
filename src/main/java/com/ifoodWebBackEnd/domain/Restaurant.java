package com.ifoodWebBackEnd.domain;

import com.ifoodWebBackEnd.domain.user.User;
import com.ifoodWebBackEnd.dtos.RestaurantRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.Instant;


@Table(name = "restaurants")
@Entity()
@SQLDelete(sql = "UPDATE restaurants SET deleted = true WHERE id=?")
@Where(clause = "deleted='false'")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String image;
    private String street;
    private String number;
    private String city;
    @JoinColumn(name = "user_id")
    private User user;
    @CreationTimestamp
    private Instant creationTimestamp;
    @UpdateTimestamp
    private Instant updateTimestamp;
    private boolean deleted = false;

    public Restaurant(RestaurantRequestDTO data, User user){
        this.name = data.name();
        this.image = data.image();
        this.street = data.street();
        this.number = data.number();
        this.city = data.city();
        this.user = user;
    }
}
