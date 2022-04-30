package com.FlightsSystem.FlightsSystem.Controlles;


import com.FlightsSystem.FlightsSystem.Facades.CustomerFacade;
import com.FlightsSystem.FlightsSystem.Poco.Customer;
import com.FlightsSystem.FlightsSystem.Poco.Flight;
import com.FlightsSystem.FlightsSystem.Poco.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class CustomerController {

    @Autowired
    CustomerFacade customerFacade;

    @GetMapping("/ticket")
    public List<Ticket> get_my_ticket(){
        return customerFacade.get_my_ticket();
    }

    @GetMapping("/flights")
    public List<Flight> get_my_flight(){
        return customerFacade.get_my_flight();
    }
    @PostMapping("/ticket")
    public void add_ticket(@RequestBody Ticket ticket) {
        customerFacade.add_ticket(ticket);
    }

    @DeleteMapping("/ticket")
    public void remove_ticket(@RequestBody Ticket ticket) {
        customerFacade.remove_ticket(ticket);
    }


    @PutMapping("/")
    public void update_customer(@RequestBody Customer customer) {
        customerFacade.update_customer(customer);

    }
}
