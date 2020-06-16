package il.ac.hit.mvc.Utilities;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetails {
    private String useremail;
    private String userpassword;


    //by default while working with hibernate you must have a public cot'r without param's
    public  UserDetails()
    {

    }
    public  UserDetails(String email,String password)
    {
        this.useremail=email;
        this.userpassword=password;
    }

    @Id
    public String getUserEmail() {
        return useremail;
    }

    public void setUserEmail(String userEmail) {
        this.useremail = userEmail;
    }

    public String getUserPassword() {
        return userpassword;
    }

    public void setUserPassword(String userPassword) {
        this.userpassword = userPassword;
    }
}
