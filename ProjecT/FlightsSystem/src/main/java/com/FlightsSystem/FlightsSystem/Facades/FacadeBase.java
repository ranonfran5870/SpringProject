package com.FlightsSystem.FlightsSystem.Facades;
import com.FlightsSystem.FlightsSystem.DAO.*;
import com.FlightsSystem.FlightsSystem.Login.Permissions;
import com.FlightsSystem.FlightsSystem.Poco.AirlineCompanies;
import com.FlightsSystem.FlightsSystem.Poco.Country;
import com.FlightsSystem.FlightsSystem.Poco.Flight;
import com.FlightsSystem.FlightsSystem.Poco.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public abstract class FacadeBase {
    @Autowired
    private FlightDAO flight;
    @Autowired
    private AirlineCompaniesDAO airlineCompanies;
    @Autowired
    private CountryDAO countryDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private UserDAO user;

    //-> Flight

    public List<Flight> get_all_flights(){
        return this.flight.getAll();
    }

    public Flight get_flight_by_id(long _id){
        return this.flight.getById(_id);
    }

    public Flight get_flights_by_parameters(int _origin_country_id,int _destination_country_id,String _date){
        return this.flight.get_flights_by_parameters(_origin_country_id,_destination_country_id,_date);
    }

    //-> AirlineCompanies
    public List<AirlineCompanies> get_all_airlines(){
        return this.airlineCompanies.getAll();
    }

    public AirlineCompanies get_airlines_by_id(long _id){
        return this.airlineCompanies.getById(_id);
    }

    //-> Country
    public List<Country> get_all_countries(){
        return this.countryDAO.getAll();
    }

    public Country get_country_by_id(int _id){
        return this.countryDAO.getById(_id);
    }


    protected boolean createNewUser(User user){
        user.user_role = 1;
        if (user.thumbnail == null || user.thumbnail.trim().isEmpty())
            user.thumbnail = "https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_1280.png";
        System.out.println(user);
        userDAO.Add(user);
        return true;
    }

    public long getUserId(String _usernamme){
        return user.get_user_by_username(_usernamme).id;
    }

}
