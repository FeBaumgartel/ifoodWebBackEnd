package com.ifoodWebBackEnd.domain;

import com.ifoodWebBackEnd.domain.user.User;
import com.ifoodWebBackEnd.dtos.RestaurantRequestDTO;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "restaurants")
@Entity()
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
    private String uf;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Restaurant(RestaurantRequestDTO data, User user){
        this.name = data.name();
        this.image = data.image();
        this.street = data.street();
        this.number = data.number();
        this.city = data.city();
        this.user = user;
    }
}
