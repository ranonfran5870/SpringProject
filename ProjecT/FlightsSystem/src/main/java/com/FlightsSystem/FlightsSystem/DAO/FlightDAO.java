package com.FlightsSystem.FlightsSystem.DAO;
import com.FlightsSystem.FlightsSystem.Poco.Flight;
import com.FlightsSystem.FlightsSystem.PostgresqlConnection.PostgresqlConnection;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class FlightDAO implements IDAO<Flight,Long>{

    List<Flight> FlightList = new ArrayList<>();
    Connection connection = PostgresqlConnection.getInstance().getConnection();
    Statement stm = PostgresqlConnection.getInstance().getStatement();
    @Override
    public List<Flight> getAll() {
        String query = "SELECT * FROM Flights";
        try {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                FlightList.add(new Flight
                        (result.getLong("id"),
                                result.getLong("airline_company_id")
                                ,result.getInt("origin_country_id")
                                ,result.getInt("destination_country_id")
                                ,result.getTimestamp("departure_time")
                                ,result.getTimestamp("landing_time")
                                , result.getInt("remaining_tickets")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FlightList;
    }

    @Override
    public Flight getById(Long _id) {
        String query = "SELECT * FROM Flights WHERE id=";
        Flight flight=null;
        try {
            var result = stm.executeQuery
                    (query + _id);
            result.next();
            flight=new Flight(result.getLong("id"),
                    result.getLong("airline_company_id")
                    ,result.getInt("origin_country_id")
                    ,result.getInt("destination_country_id")
                    ,result.getTimestamp("departure_time")
                    ,result.getTimestamp("landing_time")
                    , result.getInt("remaining_tickets"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    @Override
    public void Add(Flight flight) {
        try {
            stm.executeUpdate("INSERT INTO Flights (\"Airline_Company_id\",\"Origin_Country_id\",\"Destination_Country_id\",\"Departure_Time\",\"Landing_Time\",\"Remaining_Tickets\") " +
                    "VALUES " +
                    "('"+flight.airline_company_id+"','"+flight.origin_country_id+"','"+flight.destination_country_id+"','"+flight.departure_time+"','"+flight.landing_time+"','"+flight.remaining_tickets+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void Remove(Flight flight) {
        try {
            stm.executeUpdate("DELETE from Flights WHERE id = "+flight.id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void Update(Flight flight) {
        try {
            stm.executeUpdate("UPDATE Flights SET " +
                    "\"Airline_Company_id\"= '"+flight.airline_company_id+"', " +
                    "\"Origin_Country_id\"= '"+flight.origin_country_id+"', " +
                    "\"Destination_Country_id\"= '"+flight.destination_country_id+"', " +
                    "\"Departure_Time\"= '"+flight.departure_time+"', " +
                    "\"Landing_Time\"= '"+flight.landing_time+"', " +
                    "\"Remaining_Tickets\"='"+ flight.remaining_tickets+
                    "' where id="+flight.id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Flight getFlightsByOriginCountryId(int _country_id){
        String query = "SELECT * FROM Flights WHERE Origin_Country_id=";
        Flight flight=null;
        try {
            var result = stm.executeQuery
                    (query + _country_id);
            result.next();
            flight=new Flight(result.getLong("id"),
                    result.getLong("airline_company_id")
                    ,result.getInt("origin_country_id")
                    ,result.getInt("destination_country_id")
                    ,result.getTimestamp("departure_time")
                    ,result.getTimestamp("landing_time")
                    , result.getInt("remaining_tickets"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }


    public Flight getFlightsByDestinationCountryId(int _country_id){
        String query = "SELECT * FROM Flights WHERE Destination_Country_id=";
        Flight flight=null;
        try {
            var result = stm.executeQuery
                    (query + _country_id);
            result.next();
            flight=new Flight(result.getLong("id"),
                    result.getLong("airline_company_id")
                    ,result.getInt("origin_country_id")
                    ,result.getInt("destination_country_id")
                    ,result.getTimestamp("departure_time")
                    ,result.getTimestamp("landing_time")
                    , result.getInt("remaining_tickets"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    public Flight getFlightsByDepartureDate(Date date){
        String query = "SELECT * FROM Flights WHERE CAST(\"Departure_Time\" AS DATE)=";
        Flight flight=null;
        try {
            var result = stm.executeQuery
                    (query + date);
            result.next();
            flight=new Flight(result.getLong("id"),
                    result.getLong("airline_company_id")
                    ,result.getInt("origin_country_id")
                    ,result.getInt("destination_country_id")
                    ,result.getTimestamp("departure_time")
                    ,result.getTimestamp("landing_time")
                    , result.getInt("remaining_tickets"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }


    public Flight getFlightsByLandingDate(Date date){
        String query = "SELECT * FROM Flights WHERE CAST(\"Landing_Time\" AS DATE)=";
        Flight flight=null;
        try {
            var result = stm.executeQuery
                    (query + date);
            result.next();
            flight=new Flight(result.getLong("id"),
                    result.getLong("airline_company_id")
                    ,result.getInt("origin_country_id")
                    ,result.getInt("destination_country_id")
                    ,result.getTimestamp("departure_time")
                    ,result.getTimestamp("landing_time")
                    , result.getInt("remaining_tickets"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }



    //-----StoredProcedure -> getFlightsByCustomer
    public List<Flight> getFlightsByCustomer(long _customer_id){

        this.FlightList.removeAll(FlightList);
        String query = "select * from getflightsbycustomer("+_customer_id+")";
        try {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                FlightList.add(new Flight (result.getLong("_id"),
                    result.getLong("_airline_company_id")
                    ,result.getInt("_origin_country_id")
                    ,result.getInt("_destination_country_id")
                    ,result.getTimestamp("_departure_time")
                    ,result.getTimestamp("_landing_time")
                    , result.getInt("_remaining_tickets")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FlightList;
    }


    public Flight get_flights_by_parameters(int _origin_counry_id,int _detination_country_id,String _date){
        String query = "select * from get_flights_by_parameters("+_origin_counry_id+","+ _detination_country_id+",'"+_date+"')";
        Flight flight=null;
        try {
            var result = stm.executeQuery
                    (query);
            result.next();
            flight=new Flight(result.getLong("_id"),
                    result.getLong("_airline_company_id")
                    ,result.getInt("_origin_country_id")
                    ,result.getInt("_destination_country_id")
                    ,result.getTimestamp("_departure_time")
                    ,result.getTimestamp("_landing_time")
                    , result.getInt("_remaining_tickets"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }


    public List<Flight> get_flights_by_airline_id(long _airline_id){
        String query = "select * from get_flights_by_airline_id("+_airline_id+")";
        Flight flight=null;
        try {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                FlightList.add(new Flight(result.getLong("_id"),
                    result.getLong("_airline_company_id")
                    ,result.getInt("_origin_country_id")
                    ,result.getInt("_destination_country_id")
                    ,result.getTimestamp("_departure_time")
                    ,result.getTimestamp("_landing_time")
                    , result.getInt("_remaining_tickets")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FlightList;
    }



    public Flight get_arrival_flights(int _country_id){
        String query = "select * from get_arrival_flights("+_country_id+")";
        Flight flight=null;
        try {
            var result = stm.executeQuery
                    (query);
            result.next();
            flight=new Flight(result.getLong("_id"),
                    result.getLong("_airline_company_id")
                    ,result.getInt("_origin_country_id")
                    ,result.getInt("_destination_country_id")
                    ,result.getTimestamp("_departure_time")
                    ,result.getTimestamp("_landing_time")
                    , result.getInt("_remaining_tickets"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }



    public Flight get_departure_flights(int _country_id){
        String query = "select * from get_departure_flights("+_country_id+")";
        Flight flight=null;
        try {
            var result = stm.executeQuery
                    (query);
            result.next();
            flight=new Flight(result.getLong("_id"),
                    result.getLong("_airline_company_id")
                    ,result.getInt("_origin_country_id")
                    ,result.getInt("_destination_country_id")
                    ,result.getTimestamp("_departure_time")
                    ,result.getTimestamp("_landing_time")
                    , result.getInt("_remaining_tickets"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }


    public void flightCounterTickes(long flightId,String temp) {
        this.FlightList.removeAll(FlightList);
        try {
            stm.executeUpdate("UPDATE Flights\n" +
                    "SET \"Remaining_Tickets\" = \"Remaining_Tickets\""+temp+"\n" +
                    "WHERE \"id\" ="+flightId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Add and remove tickets +1 / -1







}