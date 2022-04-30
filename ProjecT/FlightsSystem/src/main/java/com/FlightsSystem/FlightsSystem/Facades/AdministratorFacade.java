package com.FlightsSystem.FlightsSystem.Facades;
import com.FlightsSystem.FlightsSystem.DAO.*;
import com.FlightsSystem.FlightsSystem.Poco.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AdministratorFacade extends AnonymousFacade{

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private AirlineCompaniesDAO a1;
    @Autowired
    private FlightDAO flightDAO;
    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private UserDAO userDAO;
    private List<Customer> CustomerList = new ArrayList<>();

    //Checking if Token isValid


    public List<Customer> get_all_customer(){
            return customerDAO.getAll();
    }
    //Get all customers from Sql if token isVaild

    public boolean  add_airline(User user, Country country,String name){
            AirlineCompanies ar = new AirlineCompanies();
            ar.user_id = user.id;
            ar.country_id = country.id;
            ar.name = name;
            a1.Add(ar);
            return true;
    }
    //Change user to Airline Role new airline

    public boolean  remove_airline(AirlineCompanies airlineCompanies){
            var flightListT = flightDAO.get_flights_by_airline_id(airlineCompanies.user_id).stream()
                    .map(test -> test.id)
                    .toList();
            var fRemove = new Flight();
            for (long num : flightListT) {
                fRemove.id = num;
                ticketDAO.RemoveTicketsByFlightId(num);
                flightDAO.Remove(fRemove);
            }
            a1.Remove(airlineCompanies);

            return true;
    }


    /*Remove airline
    Get airline flights
    remove all the ticket+flight airline
     */

    public boolean  adminAddCustomer(User user,Customer customer){
            addCustomer(user, customer);
            UserDAO userDAO = new UserDAO();
            User newUser = userDAO.get_user_by_username(user.username);
            if (newUser != null) {
                if (customerDAO.get_customer_by_username(newUser.username) != null) {
                    System.out.println("Congratulations, you have successfully registered!");
                    return true;
                }
            }
            return true;
    }

    //Use AnonymousFacade(addCustomer(user, customer)) Add new user Role=Customer

    public boolean  adminAddAmin(User user,Customer customer){
            addCustomer(user, customer);
            UserDAO userDAO = new UserDAO();
            User newUser = userDAO.get_user_by_username(user.username);
            if (newUser != null) {
                if (customerDAO.get_customer_by_username(newUser.username) != null) {
                    userDAO.addRoleUser(user.username, 3);
                    System.out.println("Congratulations, you have successfully registered!");
                    return true;
                }
            }
            return true;
    }

    //Use AnonymousFacade(addCustomer(user, customer)) Add new user Role=Admin

    public boolean  adminRemoveCustomer(Customer customer) {
            var ticketRemove = new Ticket();
            ticketRemove.customer_id = customer.id;
            ticketDAO.Remove(ticketRemove);
            customerDAO.Remove(customer);
            var userRemove = new User();
            userRemove.id = customer.userId;
            userDAO.Remove(userRemove);
            return true;
    }

    //Remove Customer+tickets + User id Done
}
