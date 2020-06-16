package il.ac.hit.mvc.controller;

import il.ac.hit.mvc.utils.UserDetails;
import il.ac.hit.mvc.model.DAOException;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController {
    public void about(HttpServletRequest request, HttpServletResponse response) throws MVCException, DAOException {//TODO

    }
    public void contact(HttpServletRequest request, HttpServletResponse response) throws MVCException, DAOException{//TODO

    }

    //DAO Usage
    public void createnewuser(HttpServletRequest request, HttpServletResponse response) throws MVCException, DAOException {

    }

    public void insert(HttpServletRequest request, HttpServletResponse response) throws MVCException, DAOException {

    }

    public void delete(HttpServletRequest request, HttpServletRequest response) throws MVCException, DAOException {

    }

    private UserDetails extractUserDetailsFromRequest(HttpServletRequest request) {
        UserDetails userDetails=new UserDetails(request.getParameter("userEmail"),request.getParameter("userPassword"));
        return userDetails;
    }

//TODO needs to implement all the methods in here because the admin and the user could use it
    // the admin will have more options so we will add methods to the admin controller

}
