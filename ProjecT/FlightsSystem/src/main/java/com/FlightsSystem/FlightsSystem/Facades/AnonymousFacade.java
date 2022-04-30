package com.FlightsSystem.FlightsSystem.Facades;
import com.FlightsSystem.FlightsSystem.DAO.CustomerDAO;
import com.FlightsSystem.FlightsSystem.DAO.UserDAO;
import com.FlightsSystem.FlightsSystem.Poco.Customer;
import com.FlightsSystem.FlightsSystem.Poco.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Component
public class  AnonymousFacade extends FacadeBase{


    private final String regexPassword = "^(?=.*[a-z])(?=."+ "*[A-Z])(?=.*\\d)"+ "(?=.*[-+_!@#$%^&*., ?]).+$";//password
    public final String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    @Autowired
    private UserDAO userDAO;

    public boolean addCustomer(User user,Customer customer){
        CustomerDAO customerDAO = new CustomerDAO();
        if (!customerDAO.CrditPhone(customer.phone_no,customer.credit_card_no)){
            System.out.println("Credit Card/Phone Number already exists");
            return false;
        }
        this.userDAO = new UserDAO();
        if (!ConfirmedUser(user)){
            System.out.println("User : more of the details is invalid");
            return false;}
        if (!ConfirmedCustomer(customer)){
            System.out.println("Customer : more of the details is invalid");
            return false;}
        createNewUser(user);
        User newUser = userDAO.get_user_by_username(user.username);
        if(newUser!=null) {
            customer.userId = newUser.id;
            customerDAO.Add(customer);
            if (customerDAO.get_customer_by_username(newUser.username) != null) {
                System.out.println("Congratulations, you have successfully registered!");
                return true;
            }
        }
        return false;
    }

    /*
    Step 1 = check if Card/Phone already exists in MyDB -> true/false
    Step2 = !Confirmation User valid -> true/false
    Step3 = !Confirmation Customer valid -> true/false
    Step4 = Create New user
    Step5 = Check if the user created in MyDB +
    Step6 = Created Customer
     */

    private boolean ConfirmedUser(User user){
        return !user.username.contains(" ")
                && Pattern.matches(regexPassword, user.password)
                && user.username.length() > 2
                && Pattern.matches(regexEmail, user.email);
    }

    //ConfirmedUser data by regex and more

    public boolean ConfirmedCustomer(Customer customer){
        return !customer.first_name.contains(" ") && customer.first_name.length() > 2
                && !customer.last_name.contains(" ") && customer.last_name.length() > 2
                && !customer.phone_no.contains(" ") && customer.phone_no.length() > 2
                && customer.address.length() > 2
                && !customer.credit_card_no.contains(" ") && customer.credit_card_no.length() > 8;
    }
    //ConfirmedCustomer data by regex and more


}
