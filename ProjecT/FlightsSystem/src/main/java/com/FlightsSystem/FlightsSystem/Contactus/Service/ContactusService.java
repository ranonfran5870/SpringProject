package com.FlightsSystem.FlightsSystem.Contactus.Service;

import com.FlightsSystem.FlightsSystem.Contactus.Repository.ContactusRepo;
import com.FlightsSystem.FlightsSystem.Contactus.DTO.Contactus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactusService {


    @Autowired
    ContactusRepo contactusRepo;

    public List<Contactus> getAllContact(){
        return (List<Contactus>) contactusRepo.findAll();
    }




    public Contactus getContactus(long id){
        var res = contactusRepo.findById(id);
        res.get();
        return res.orElse(null);
    }

    public void addContactus(Contactus contactus){
        contactusRepo.save(contactus);
    }

}
