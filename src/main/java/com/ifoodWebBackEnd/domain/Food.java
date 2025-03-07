package com.ifoodWebBackEnd.domain;

import com.ifoodWebBackEnd.domain.user.User;
import com.ifoodWebBackEnd.dtos.FoodRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.Instant;

@Table(name = "foods")
@Entity()
@SQLDelete(sql = "UPDATE foods SET deleted = true WHERE id=?")
@Where(clause = "deleted='false'")
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
    private String name;
    private Double price;
    private String image;
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @JoinColumn(name = "user_id")
    private User user;
    @CreationTimestamp
    private Instant creationTimestamp;
    @UpdateTimestamp
    private Instant updateTimestamp;
    private boolean deleted = false;

    public Food(FoodRequestDTO data, Restaurant restaurant, User user){
        this.name = data.name();
        this.price = data.price();
        this.image = data.image();
        this.restaurant = restaurant;
        this.user = user;
    }
}
