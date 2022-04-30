package com.FlightsSystem.FlightsSystem.Contactus.Repository;

import com.FlightsSystem.FlightsSystem.Contactus.DTO.Contactus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


//@Repository
//@Component
public interface ContactusRepo extends CrudRepository<Contactus,Long> {
}
