package il.ac.hit.mvc.Utilities;

public class UserDetails {
    private String userEmail;
    private String userPassword;


    public  UserDetails(String userEmail,String userPassword)
    {
        this.userEmail=userEmail;
        this.userPassword=userPassword;
    }
    public String getUserEmail() {
        return userEmail;
    }


    public String getUserPassword() {
        return userPassword;
    }



}
