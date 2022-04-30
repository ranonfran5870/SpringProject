package com.FlightsSystem.FlightsSystem.Facades;
import com.FlightsSystem.FlightsSystem.DAO.AirlineCompaniesDAO;
import com.FlightsSystem.FlightsSystem.DAO.FlightDAO;
import com.FlightsSystem.FlightsSystem.DAO.TicketDAO;
import com.FlightsSystem.FlightsSystem.Facades.SecurityContext.SContext;
import com.FlightsSystem.FlightsSystem.Poco.AirlineCompanies;
import com.FlightsSystem.FlightsSystem.Poco.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class AirlineFacade extends AnonymousFacade{
    private final List<AirlineCompanies> airlineList = new ArrayList<>();
    @Autowired
    private AirlineCompaniesDAO a1;
    @Autowired
    private FlightDAO f1;
    @Autowired
    private TicketDAO ticketDAO;
    private final List<Flight> FlightList = new ArrayList<>();

    public boolean update_airline (AirlineCompanies airlineCompanies) {
        var countryListT = get_all_countries().stream()
                .map(test -> test.id)
                .toList();
        if (countryListT.contains(airlineCompanies.country_id)) {
            airlineCompanies.user_id = getUserId(SContext.getUserName());
            airlineCompanies.id = a1.get_airline_by_username(SContext.getUserName().toLowerCase()).id;
            a1.Update(airlineCompanies);
            return true;
        } else
            System.out.println("Not possible Country  id");
        return false;
    }

    public boolean add_flight (Flight flight){
            if (!confirmedFlight(flight)) {
                System.out.println("This flight is illegal");
                return false;
            }
            flight.airline_company_id = a1.get_airline_by_username(SContext.getUserName().toLowerCase()).id;
            f1.Add(flight);
            return true;
    }
    /*
    this function
    Checks that all information is correct and logical use (confirmedFlight()) and add new flight
     */

    public boolean remove_flight (Flight flight){
        if (get_flight_by_id(flight.id).airline_company_id == a1.get_airline_by_username(SContext.getUserName().toLowerCase()).id) {
                ticketDAO.RemoveTicketsByFlightId(flight.id);
                f1.Remove(flight);
                return true;
        }
        System.out.println("You can only update your available flights");
        return false;
    }

    public List<Flight> get_my_flight(){

            return f1.get_flights_by_airline_id(a1.get_airline_by_username(SContext.getUserName().toLowerCase()).id);
    }


    public boolean update_flight (Flight flight){
            if (get_flight_by_id(flight.id).airline_company_id == a1.get_airline_by_username(SContext.getUserName().toLowerCase()).id) {
                if (!confirmedFlight(flight)) {
                    System.out.println("This flight is illegal");
                    return false;
                }
                flight.airline_company_id = a1.get_airline_by_username(SContext.getUserName().toLowerCase()).id;
                f1.Update(flight);
                return true;
            }
            System.out.println("You can only update your available flights");
            return false;
    }
    /*
    this function get 'flight'
    Checks that all information is correct and logical use (confirmedFlight()) and update
     */

    private boolean confirmedFlight(Flight flight){
        var countryListT = get_all_countries().stream()
                .map(test -> test.id)
                .distinct()
                .toList();
        Date date = new Date();
        String sDeparture_time = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(flight.departure_time);
        String sLanding_time = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(flight.landing_time);
        int stempVsNoww = sDeparture_time.compareTo(String.valueOf(new Timestamp(date.getTime())));
        int timestempCheack = sDeparture_time.compareTo(sLanding_time);
        return timestempCheack != 0 && timestempCheack <= 0 && flight.origin_country_id != flight.destination_country_id && flight.remaining_tickets > 0 && countryListT.contains(flight.origin_country_id) && countryListT.contains(flight.destination_country_id) && stempVsNoww >= 0;
    }
    //This function get flight and Checks that all information is correct and logical
}
