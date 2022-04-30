package com.FlightsSystem.FlightsSystem.DAO;
import com.FlightsSystem.FlightsSystem.Poco.AirlineCompanies;
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
public class AirlineCompaniesDAO implements IDAO<AirlineCompanies,Long>{

    List<AirlineCompanies> AirlineCompaniesList = new ArrayList<>();
    Connection connection = PostgresqlConnection.getInstance().getConnection();
    Statement stm = PostgresqlConnection.getInstance().getStatement();

    //----getAll
    @Override
    public List<AirlineCompanies> getAll() {
        String query = "SELECT * FROM airline_companies";
        try {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                AirlineCompaniesList.add(new AirlineCompanies
                        (result.getLong("id")
                                ,result.getString("name")
                                ,result.getInt("country_id")
                                ,result.getLong("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AirlineCompaniesList;
    }

    //----getById
    @Override
    public AirlineCompanies getById(Long _id) {
        String query = "SELECT * FROM airline_companies WHERE id=";
        AirlineCompanies airlineCompanies=null;
        try {
            var result = stm.executeQuery
                    (query + _id);
            result.next();
            airlineCompanies=new AirlineCompanies(result.getLong("id")
                    ,result.getString("name")
                    ,result.getInt("country_id")
                    ,result.getLong("user_id"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompanies;
    }

    @Override
    public void Add(AirlineCompanies airlineCompanies) {
        try {
            stm.executeUpdate("INSERT INTO airline_companies (\"Name\",\"Country_id\",\"User_id\") " +
                    "VALUES " +
                    "('"+airlineCompanies.name+"','"+airlineCompanies.country_id+"','"+airlineCompanies.user_id+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Remove(AirlineCompanies airlineCompanies) {

        try {
            stm.executeUpdate("DELETE from airline_companies WHERE id = "+airlineCompanies.id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void Update(AirlineCompanies airlineCompanies) {

        try {
            System.out.println("hjjhjhj");
            System.out.println(airlineCompanies);
            stm.executeUpdate("UPDATE airline_companies SET " +
                    "\"Name\"= '"+airlineCompanies.name+"', " +
                    "\"Country_id\"= '"+airlineCompanies.country_id+"', " +
                    "\"User_id\"='"+ airlineCompanies.user_id+
                    "' where id="+airlineCompanies.id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public AirlineCompanies getAirlinesByCountry(int _country_id){
        String query = "SELECT * FROM airline_companies WHERE Country_id=";
        AirlineCompanies airlineCompanies=null;
        try {
            var result = stm.executeQuery
                    (query + _country_id);
            result.next();
            airlineCompanies=new AirlineCompanies(result.getLong("id")
                    ,result.getString("name")
                    ,result.getInt("country_id")
                    ,result.getLong("user_id"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompanies;
    }


    //-----StoredProcedure
    public Ticket getBsdadyId(Long _id) {
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
    public AirlineCompanies get_airline_by_username(String  _username){
        String query = "select * from get_airline_by_username('"+_username+"')";
        AirlineCompanies airlineCompanies=null;
        try {
            var result = stm.executeQuery(query);
            result.next();
            airlineCompanies=new AirlineCompanies(result.getLong("_id")
                    ,result.getString("_name")
                    ,result.getInt("_country_id")
                    ,result.getLong("_user_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompanies;
    }



}
