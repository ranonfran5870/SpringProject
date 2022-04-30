package com.FlightsSystem.FlightsSystem.Controlles;

import com.FlightsSystem.FlightsSystem.Facades.AirlineFacade;
import com.FlightsSystem.FlightsSystem.Poco.AirlineCompanies;
import com.FlightsSystem.FlightsSystem.Poco.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Airline")
@PreAuthorize("hasRole('ROLE_AIRLINE')")
public class AirlineController {
    @Autowired
    AirlineFacade airlineFacade;


    @GetMapping("/test")
    public String test(){
        return "test";
    }


    @PutMapping("/airline")
    public void update_airline(@RequestBody AirlineCompanies airlineCompanies) {
        airlineFacade.update_airline(airlineCompanies);

    }

    @PostMapping("/flight")
    public void add_flight(@RequestBody Flight flight) {
        airlineFacade.add_flight(flight);
    }


    @DeleteMapping("/flight")
    public void remove_flight(@RequestBody Flight flight) {
        airlineFacade.remove_flight(flight);
    }

    @PutMapping("/flight")
    public void update_flight(@RequestBody Flight flight) {
        airlineFacade.update_flight(flight);
    }

    @GetMapping("/flight")
    public List<Flight> get_my_flight(@RequestBody Flight flight) {
        return airlineFacade.get_my_flight();
    }




}
