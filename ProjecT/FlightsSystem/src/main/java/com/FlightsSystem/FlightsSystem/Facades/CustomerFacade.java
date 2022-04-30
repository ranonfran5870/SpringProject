package com.FlightsSystem.FlightsSystem.Facades;
import com.FlightsSystem.FlightsSystem.DAO.CustomerDAO;
import com.FlightsSystem.FlightsSystem.DAO.FlightDAO;
import com.FlightsSystem.FlightsSystem.DAO.TicketDAO;
import com.FlightsSystem.FlightsSystem.DAO.UserDAO;
import com.FlightsSystem.FlightsSystem.Facades.SecurityContext.SContext;
import com.FlightsSystem.FlightsSystem.Poco.Customer;
import com.FlightsSystem.FlightsSystem.Poco.Flight;
import com.FlightsSystem.FlightsSystem.Poco.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerFacade extends AnonymousFacade{
    private final List<Flight> FlightList = new ArrayList<>();
    private final List<Ticket> TicketList = new ArrayList<>();
    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private FlightDAO f1;
    @Autowired
    private CustomerDAO cu;
    @Autowired
    private UserDAO user;




    public boolean  update_customer(Customer customer){
        customer.id = cu.getById(getUserId(SContext.getUserName())).id;
        customer.userId = getUserId(SContext.getUserName());
        if (!ConfirmedCustomer(customer)){
            System.out.println("Customer : more of the details is invalid");
            return false;}

        if (!cu.get_customer_by_username(SContext.getUserName()).credit_card_no.equals(customer.credit_card_no) || !cu.get_customer_by_username(SContext.getUserName()).phone_no.equals(customer.phone_no))
            if (!cu.CrditPhone(customer.phone_no, customer.credit_card_no)) {
                System.out.println("Credit Card/Phone Nomber already exists");
                return false;
            }
        cu.Update(customer);
        return true;
    }
    /*
    Step1 = Allows customer update only his own details
    Step2 = Confirmation Customer valid -> true/false
    Step3 = check if Card/Phone already exists in MyDB
    Step4 = Update
     */

    public List<Ticket> get_my_ticket(){
        return ticketDAO.get_tickets_by_customer(getUserId(SContext.getUserName()));
    }


    public boolean remove_ticket(Ticket ticket) {
        if (!ticketDAO.get_tickets_by_customer(getUserId(SContext.getUserName())).isEmpty()) {
            if (ticket.customer_id == getUserId(SContext.getUserName())) {
                ticketDAO.RemoveTickesCustomer(ticket);
                f1.flightCounterTickes(ticket.flight_id, "+1");
                return true;
            }
        }
        return false;
    }

    /*
    Step1 = Cheack if list have tickets in the list
    Step2 = Cheack if the customer ticket equals to the login token
    Step3 = Remove ticket by ticket_id
    Step4 = Flight count temp -1
     */

    public boolean add_ticket(Ticket ticket){
            var tickListT = ticketDAO.get_tickets_by_customer(getUserId(SContext.getUserName())).stream()
                    .map(test -> test.flight_id)
                    .toList();
            var flightListT = get_all_flights().stream()
                    .map(test -> test.id)
                    .toList();
            if (!tickListT.contains(ticket.flight_id)) {
                System.out.println("here");
                if (flightListT.contains(ticket.flight_id) && get_flight_by_id(ticket.flight_id).remaining_tickets > 0) {
                    ticket.customer_id = cu.get_customer_by_username(SContext.getUserName().toLowerCase()).id;
                    ticketDAO.Add(ticket);
                    f1.flightCounterTickes(ticket.flight_id, "-1");
                    return true;

                }
            }
        return false;
    }
    /*
    Step1 = Checks if already has a flight ticket -> break
    Step1 = Checking if there flight -> break
    Step2 = Checking remaining_tickets -> break
    Step3 = add ticket
    Step4 = dsafdsd
     */

    public List<Flight> get_my_flight(){
            return f1.getFlightsByCustomer(getUserId(SContext.getUserName()));
    }
    //return all customer flight

}
