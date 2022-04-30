package com.FlightsSystem.FlightsSystem.DAO;
import com.FlightsSystem.FlightsSystem.Poco.User;
import com.FlightsSystem.FlightsSystem.PostgresqlConnection.PostgresqlConnection;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserDAO implements IDAO<User,Long>{

    //String url = "//localhost:5432/AirlineDB";                       //url Send to SqlConnect -> MyDB
    List<User> UserList = new ArrayList<>();
    Connection connection = PostgresqlConnection.getInstance().getConnection();
    Statement stm = PostgresqlConnection.getInstance().getStatement();
    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM Users";
        try {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                UserList.add(new User
                        (result.getInt("id"),
                                result.getString("username")
                                , result.getString("password"),
                                result.getString("email"),
                                result.getInt("user_role")
                                , result.getString("thumbnail")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return UserList;
    }

    @Override
    public User getById(Long _id) {
        String query = "SELECT * FROM Users WHERE id=";
        User user=null;
        try {
            var result = stm.executeQuery
                    (query + _id);
            result.next();
            user=new User(result.getInt("id"),
                    result.getString("username")
                    , result.getString("password"),
                    result.getString("email"),
                    result.getInt("user_role")
                    , result.getString("thumbnail"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void Add(User user) {
        int result=0;
        try {
            result=stm.executeUpdate("INSERT INTO Users (\"Username\",\"Password\",\"Email\",\"User_Role\",\"Thumbnail\") " +
                    "VALUES " +
                    "('"+user.username+"','"+user.password+"','"+user.email+"','"+user.user_role+"','"+user.thumbnail+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result==0)
            System.out.println("Fail add user");
    }

    @Override
    public void Remove(User user) {
        try {
            stm.executeUpdate("DELETE from Users WHERE id = "+user.id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void Update(User user) {
        try {
            stm.executeUpdate("UPDATE users SET " +
                    "\"Username\"= '"+user.username+"', " +
                    "\"Password\"= '"+user.password+"', " +
                    "\"Email\"= '"+user.email+"', " +
                    "\"User_role\"= '"+user.username+"', " +
                    "\"Thumbnail\"= '"+user.thumbnail+"', " +
                    "' where id="+user.id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //-----StoredProcedure



    public User get_user_by_username(String _username) {
        String query = "select * from get_user_by_username('"+_username+"')";
        User user=null;
        try {
            var result = stm.executeQuery(query);
            result.next();
            user=new User(result.getInt("_id"),
                    result.getString("_username")
                    , result.getString("_password"),
                    result.getString("_email"),
                    result.getInt("_user_role")
                    , result.getString("_thumbnail"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean LoginConfirmation(String _username,String _password) {
        String query = "SELECT * FROM users where lower(\"Username\") = '"+_username+"' and \"Password\" = '"+_password+"'";

        try {
            if (_username != null && _password != null) {
                var result = stm.executeQuery(query);
                return result.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int[] LoginPullUserRole(String _username,String _password) {
        String query = "SELECT * FROM users where lower(\"Username\") = '"+_username.toLowerCase()+"' and \"Password\" = '"+_password+"'";
        int[] ans = new int[2]; //change long
        try {
            if (_username != null && _password != null) {
                var result = stm.executeQuery(query);
                if (result.next()){
                    ans[0] = result.getInt("id");
                    ans[1] = result.getInt("User_Role");
                    return ans;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public boolean addRoleUser(String _name,int _role){
        int result=0;
        try {
            result=stm.executeUpdate("UPDATE users SET " +
                    "\"User_Role\"= '"+_role+"'where \"Username\"='"+_name+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result==0?false:true;
    }

}
