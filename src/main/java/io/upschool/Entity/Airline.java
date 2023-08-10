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
@Data 
@Getter
@Setter
@Table(name="airlines")
@AllArgsConstructor 
@NoArgsConstructor
@Builder
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(name="airline_name", nullable = false,length = 100)
    private String airlineName;
   
   @Column(name="airline_code", nullable = false,length = 10)
   private String airlineCode;
   
   @Column(name="airline_isActive")
   private boolean airlineIsActive;
  

	
}