package il.ac.hit.mvc.utils;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetails {
    private String emil;
    private String password;


    //by default while working with hibernate you must have a public cot'r without param's
    public UserDetails() {

    }

    public UserDetails(String email, String password) {
        this.emil = email;
        this.password = password;
    }

    @Id
    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

