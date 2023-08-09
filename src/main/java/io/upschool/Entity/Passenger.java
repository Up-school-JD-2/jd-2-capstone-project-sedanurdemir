package io.upschool.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "passengers")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Passenger {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;

    @Column(name="passenger_name",nullable = false)
    private String firstName;

    @Column(name="passenger_surname",nullable = false)
    private String lastName;
    
    

}

