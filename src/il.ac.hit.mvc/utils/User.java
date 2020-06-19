package il.ac.hit.mvc.utils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.*;


public class User {


    private String email;
    private String password;


    //by default while working with hibernate you must have a public cot'r without param's

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString()
    {
        String retStr=
                "\n User Email: "+ getEmail()
                +"\n User Password: "+getPassword();
        return retStr;

    }
}
