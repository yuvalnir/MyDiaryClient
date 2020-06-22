package il.ac.hit.mvc.utils;

public class User {
    private String email;
    private String password;


    //by default while working with hibernate you must have a public cot'r without param's
    public User() {

    }

    public User(String email, String password) {
        this.emil = email;
        this.password = password;
    }

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

    @Override
    public String toString()
    {
        String retStr=
                "\n User Email: "+ getEmail()
                +"\n User Password: "+getPassword();
        return retStr;

    }
}

