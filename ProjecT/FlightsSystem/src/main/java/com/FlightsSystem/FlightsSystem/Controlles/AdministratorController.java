package com.FlightsSystem.FlightsSystem.Controlles;

import com.FlightsSystem.FlightsSystem.Facades.AdministratorFacade;
import com.FlightsSystem.FlightsSystem.Facades.AirlineFacade;
import com.FlightsSystem.FlightsSystem.Poco.AirlineCompanies;
import com.FlightsSystem.FlightsSystem.Poco.Customer;
import com.FlightsSystem.FlightsSystem.Poco.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdministratorController {

    @Autowired
    AdministratorFacade administratorFacade;

    @GetMapping("/customer")
    public List<Customer> getAll(){
        return administratorFacade.get_all_customer();
    }

    @PostMapping("/customer")
    public void adminAddCustomer(@RequestBody User user, @RequestBody Customer customer) {
        administratorFacade.adminAddCustomer(user,customer);
    }

    @DeleteMapping("/airline")
    public void remove_airline(@RequestBody AirlineCompanies airlineCompanies) {
        administratorFacade.remove_airline(airlineCompanies);
    }


    @PostMapping("/admin")
    public void adminAddAmin(@RequestBody User user, @RequestBody Customer customer) {
        administratorFacade.adminAddAmin(user,customer);
    }


    @DeleteMapping("/customer")
    public void adminRemoveCustomer(@RequestBody Customer customer) {
        administratorFacade.adminRemoveCustomer(customer);
    }

}
