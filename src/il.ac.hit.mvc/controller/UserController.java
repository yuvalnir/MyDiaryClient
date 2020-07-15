package il.ac.hit.mvc.controller;

import il.ac.hit.mvc.utils.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController {

    public void Login(HttpServletRequest request, HttpServletResponse response, String data) {
        RequestDispatcher dispatcher = null;
        dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");

    }

    public void Logout(HttpServletRequest request, HttpServletResponse response, String data) {

    }

    public void Home(HttpServletRequest request, HttpServletResponse response, String data) {

    }

    public void AddEvent(HttpServletRequest request, HttpServletResponse response, String data) {

    }

    public void Graph(HttpServletRequest request, HttpServletResponse response, String data) {

    }

    public void UserSettings(HttpServletRequest request, HttpServletResponse response, String data) {

    }
}