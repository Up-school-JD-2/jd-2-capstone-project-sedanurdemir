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
@Table(name="airports")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "airport_code", length = 10, nullable = false)
	    private String airportCode;

	    @Column(name = "airport_name", length = 100, nullable = false)
	    private String airportName;
	    
	    @Column(name="city", length = 10)
	    private String cityName;
	    
	    @Column(name="airport_isActive", nullable = false)
	    private boolean airportIsActive=true;
}
