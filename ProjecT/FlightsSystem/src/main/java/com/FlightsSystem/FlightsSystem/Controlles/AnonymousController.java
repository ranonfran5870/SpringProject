package com.FlightsSystem.FlightsSystem.Controlles;

import com.FlightsSystem.FlightsSystem.Facades.AnonymousFacade;
import com.FlightsSystem.FlightsSystem.Facades.CustomerFacade;
import com.FlightsSystem.FlightsSystem.Facades.FacadeBase;
import com.FlightsSystem.FlightsSystem.Poco.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AnonymousController {
    @Autowired
    AnonymousFacade anonymousFacade;

    @GetMapping("/flight")
    public List<Flight> get_all_flights(){
        return anonymousFacade.get_all_flights();
    }


    @GetMapping("/flight/{id}")

    public Flight get_flight_by_id(@PathVariable("id") int id) {
        return anonymousFacade.get_flight_by_id(id);
    }

    @GetMapping("/flight/parameters")

    public Flight get_flights_by_parameters(@RequestParam(value = "_origin_country_id") Integer  _origin_country_id,
                                            @RequestParam(value = "_destination_country_id") Integer  _destination_country_id,
                                            @RequestParam(value = "_date") String  _date) {
        return anonymousFacade.get_flights_by_parameters(_origin_country_id,_destination_country_id,_date);
    }

    @GetMapping("/airlines")
    public List<AirlineCompanies> get_all_airlines(){
        System.out.println("her flight");
        return anonymousFacade.get_all_airlines();
    }

    @GetMapping("/airlines/{id}")

    public AirlineCompanies get_airlines_by_id(@PathVariable("id") int id) {
        return anonymousFacade.get_airlines_by_id(id);
    }

    @GetMapping("/countries")
    public List<Country> get_all_countries(){
        return anonymousFacade.get_all_countries();
    }
}
