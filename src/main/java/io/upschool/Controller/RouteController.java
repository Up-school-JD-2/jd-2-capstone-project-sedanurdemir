package io.upschool.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.upschool.DTO.RouteSaveRequest;
import io.upschool.DTO.RouteSaveResponse;
import io.upschool.DTO.RouteUpdateRequest;
import io.upschool.Entity.Route;
import io.upschool.Service.RouteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

	private final RouteService routeService;
	
	@PostMapping
    public RouteSaveResponse saveRoute(@RequestBody RouteSaveRequest routeSaveRequest) {
        return routeService.save(routeSaveRequest);
    }
	
	
    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public Route findRouteById(@PathVariable Long id) {
        return routeService.findRouteById(id);
    }

    @PutMapping("/{id}")
    public RouteSaveResponse updateRoute(@PathVariable Long id, @RequestBody RouteUpdateRequest routeUpdateRequest) {
        routeUpdateRequest.setId(id);
        return routeService.update(routeUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable Long id) {
        routeService.delete(id);
    }
    
   
}

