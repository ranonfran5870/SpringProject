package com.FlightsSystem.FlightsSystem.DAO;
import com.FlightsSystem.FlightsSystem.Poco.Customer;
import com.FlightsSystem.FlightsSystem.PostgresqlConnection.PostgresqlConnection;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerDAO implements IDAO<Customer,Long>{

    List<Customer> CustomerList = new ArrayList<>();
    Connection connection = PostgresqlConnection.getInstance().getConnection();
    Statement stm = PostgresqlConnection.getInstance().getStatement();;
    @Override
    public List<Customer> getAll() {
        System.out.println("in DTOO");
        String query = "SELECT * FROM Customers";
        try {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                CustomerList.add(new Customer
                        (result.getLong("id"),
                                result.getString("first_name"),
                                result.getString("last_name"),
                                result.getString("address"),
                                result.getString("phone_no"),
                                result.getString("credit_card_no")
                                , result.getLong("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CustomerList;
    }

    @Override
    public Customer getById(Long _id) {
        String query = "SELECT * FROM Customers WHERE \"User_id\"=";
        Customer customer=null;
        try {
            var result = stm.executeQuery
                    (query + _id);
            result.next();
            customer=new Customer(result.getLong("id"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getString("address"),
                    result.getString("phone_no"),
                    result.getString("credit_card_no")
                    , result.getLong("user_id"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void Add(Customer customer) {

        try {
            stm.executeUpdate("INSERT INTO Customers (\"First_Name\",\"Last_Name\",\"Address\",\"Phone_No\",\"Credit_Card_No\",\"User_id\") " +
                    "VALUES " +
                    "('"+customer.first_name+"','"+customer.last_name+"','"+customer.address+"','"+customer.phone_no+"','"+customer.credit_card_no+"',"+customer.userId+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Remove(Customer customer) {

        try {
            stm.executeUpdate("DELETE from customers WHERE id = "+customer.id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void Update(Customer customer) {

        try {
            stm.executeUpdate("UPDATE customers SET " +
                    "\"First_Name\"= '"+customer.first_name+"', " +
                    "\"Last_Name\"= '"+customer.last_name+"', " +
                    "\"Address\"= '"+customer.address+"', " +
                    "\"Phone_No\"= '"+customer.phone_no+"', " +
                    "\"Credit_Card_No\"= '"+customer.credit_card_no+"', " +
                    "\"User_id\"='"+ customer.userId+
                    "' where id="+customer.id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-----StoredProcedure


    public Customer get_customer_by_username(String _username) {
        String query = "select * from get_customer_by_username('"+_username+"')";
        Customer customer=null;
        try {
            ResultSet result = stm.executeQuery(query);
            if(result.next())
            customer=new Customer(result.getLong("_id"),
                    result.getString("_first_name"),
                    result.getString("_last_name"),
                    result.getString("_address"),
                    result.getString("_phone_no"),
                    result.getString("_credit_card_no")
                    , result.getLong("_user_id"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
//,String _crdit

    public boolean CrditPhone(String _phone,String _credit){
        int x= 0;
        try {
            var result = stm.executeQuery("select * from customers where \"Phone_No\" = '"+_phone+"'");
            if (result.next()){
                x++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            var result = stm.executeQuery("select * from customers where \"Credit_Card_No\" = '"+_credit+"'");
            if (result.next()){
                x++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x==0;
    }


}
