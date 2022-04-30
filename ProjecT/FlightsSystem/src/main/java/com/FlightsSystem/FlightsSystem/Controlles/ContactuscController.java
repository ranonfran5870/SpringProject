package com.FlightsSystem.FlightsSystem.Controlles;

import com.FlightsSystem.FlightsSystem.Contactus.Repository.ContactusRepo;
import com.FlightsSystem.FlightsSystem.Contactus.DTO.Contactus;
import com.FlightsSystem.FlightsSystem.Contactus.Service.ContactusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Contactus")
public class ContactuscController {

    @Autowired
    ContactusService contactusService;



    @GetMapping("/")
    public List<Contactus> getAllContactus(){
        return contactusService.getAllContact();

    }

    @GetMapping("/{id}")
    public Contactus getAllById(@PathVariable int id){

        var ress = contactusService.getContactus(id);
        return (ress != null ? ress: new Contactus());

    }



    @PostMapping("/")
    public void add(@RequestBody Contactus contactus){
        contactusService.addContactus(contactus);
    }
}
