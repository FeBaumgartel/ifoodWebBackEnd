package com.ifoodWebBackEnd.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ifoodWebBackEnd.dtos.FoodRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "foods")
@Entity()
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    private String name;
    private Double price;
    private String image;

    public Food(FoodRequestDTO data){
        this.name = data.name();
        this.price = data.price();
        this.image = data.image();
    }
}
