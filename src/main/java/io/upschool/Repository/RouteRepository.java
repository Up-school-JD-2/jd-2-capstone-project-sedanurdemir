package io.upschool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.upschool.Entity.Route;
@Repository
public interface RouteRepository extends JpaRepository<Route, Long>{

	Route findByRouteId(Long id);
	
}