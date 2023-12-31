package io.upschool.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "routes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Route {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    private Airport arrivalAirport;
    
    @Column(name="route_isActive", nullable = false)
    private boolean routeIsActive=true;
}
