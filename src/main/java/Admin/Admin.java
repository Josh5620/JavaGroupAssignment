package Admin;
import UserLogin.User;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dylan
 */
public class Admin extends User {


    Admin admin = new Admin();
        Map<String, String> roleMap = new HashMap<>() {{
        put("Admin", "Admin");
        put("Finance Manager (FM)", "FM");
        put("Inventory Manager (IM)", "IM");
        put("Purchase Manager (PM)", "PM");
        put("Sales Manager (SM)", "SM");
    }};
      
        
    public void AddUser(String username, String Passwd, String Role){
        String User = String.join("|", username, Passwd, Role);
        
        
    };
     
    public void DeleteUser(){
        
        
    }
}
