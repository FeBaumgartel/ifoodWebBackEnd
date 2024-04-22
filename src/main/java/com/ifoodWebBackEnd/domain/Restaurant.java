package com.ifoodWebBackEnd.domain;

import com.ifoodWebBackEnd.dtos.RestaurantRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "restaurants")
@Entity(name = "restaurants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String street;
    private String number;
    private String city;

    public Restaurant(RestaurantRequestDTO data){
        this.name = data.name();
        this.image = data.image();
        this.street = data.street();
        this.number = data.number();
        this.city = data.city();
    }
}
