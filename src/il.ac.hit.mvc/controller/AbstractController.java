package il.ac.hit.mvc.controller;

import il.ac.hit.mvc.utils.User;
import il.ac.hit.mvc.model.DAOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController {

    //DAO Usage
    public void creatNewUser(HttpServletRequest request, HttpServletResponse response) throws MVCException, DAOException {

    }

    public void insert(HttpServletRequest request, HttpServletResponse response) throws MVCException, DAOException {

    }

    public void delete(HttpServletRequest request, HttpServletRequest response) throws MVCException, DAOException {

    }

    private User extractUserDetailsFromRequest(HttpServletRequest request) {
        User user = new User(request.getParameter("userEmail"),request.getParameter("userPassword"));
        return user;
    }


//TODO needs to implement all the methods in here because the admin and the user could use it
    // the admin will have more options so we will add methods to the admin controller

}
