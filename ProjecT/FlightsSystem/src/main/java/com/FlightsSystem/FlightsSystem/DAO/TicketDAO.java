package com.FlightsSystem.FlightsSystem.DAO;
import com.FlightsSystem.FlightsSystem.Poco.Ticket;
import com.FlightsSystem.FlightsSystem.PostgresqlConnection.PostgresqlConnection;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Service
public class TicketDAO implements IDAO<Ticket,Long>{

    List<Ticket> TicketList = new ArrayList<>();
    Connection connection = PostgresqlConnection.getInstance().getConnection();
    Statement stm = PostgresqlConnection.getInstance().getStatement();
    @Override
    public List<Ticket> getAll() {
        String query = "SELECT * FROM Tickets";
        try {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                TicketList.add(new Ticket
                        (result.getLong("id"),
                                result.getLong("flight_id")
                                , result.getLong("customer_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TicketList;
    }

    @Override
    public Ticket getById(Long _id) {
        String query = "SELECT * FROM Tickets WHERE id=";
        Ticket ticket=null;
        try {
            var result = stm.executeQuery
                    (query + _id);
            result.next();
            ticket=new Ticket(result.getLong("id"),
                    result.getLong("flight_id")
                    , result.getLong("customer_id"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public void Add(Ticket ticket) {

        try {
            stm.executeUpdate("INSERT INTO Tickets (\"Flight_id\",\"Customer_id\") " +
                    "VALUES " +
                    "('"+ticket.flight_id+"','"+ticket.customer_id+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Remove(Ticket ticket) {

        try {
            System.out.println("hereeeeee");
            System.out.println(ticket.customer_id);
            stm.executeUpdate("DELETE from Tickets WHERE \"Customer_id\" = "+ticket.customer_id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void RemoveTickesCustomer(Ticket ticket) {

        try {
            stm.executeUpdate("DELETE from Tickets WHERE \"Customer_id\" = "+ticket.customer_id+" AND \"Flight_id\" = "+ticket.flight_id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Ticket ticket) {
        try {
            stm.executeUpdate("UPDATE Tickets SET " +
                    "\"Flight_id\"= '"+ticket.flight_id+"', " +
                    "\"Customer_id\"='"+ ticket.customer_id+
                    "' where id="+ticket.id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> get_tickets_by_customer(long _customer_id) {
        this.TicketList.removeAll(TicketList);
        String query = "select * from get_tickets_by_customer("+_customer_id+")";
        try {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                TicketList.add(new Ticket(result.getLong("_id"),
                    result.getLong("_flight_id")
                    , result.getLong("_customers_id")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TicketList;
    }



    public void RemoveTicketsByFlightId(long flight_id) {

        try {
            stm.executeUpdate("DELETE from Tickets WHERE \"Flight_id\" = "+flight_id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
