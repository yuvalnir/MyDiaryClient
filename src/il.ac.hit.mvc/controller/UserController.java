package il.ac.hit.mvc.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;

public class UserController {

    public void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
        JsonObject jsonObject = new JsonObject();
        String email = request.getParameter("email");
        jsonObject.add("email", JsonParser.parseString(email));
        String password = request.getParameter("password");
        jsonObject.add("password", JsonParser.parseString(password));

        System.out.println("Email is " + email); //remove later
        System.out.println("Password is " + password); //remove later
        System.out.println(jsonObject.toString()); //remove later

        HttpRequest userRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/mydiary/api/user/verify"))
                .headers("Content-Type", "application/json") //might not be needed, needs to be tested
                .POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                .build();


        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<Void> userResponse = null;
        try {
            userResponse = client.send(userRequest, HttpResponse.BodyHandlers.discarding()); //might be HttpResponse.BodyHandlers.ofInputStream()
            System.out.println(userResponse.toString()); //remove later
            System.out.println(userResponse.body()); //remove later
        } catch (InterruptedException e) {
            System.out.println("Something went wrong with sending request...");
            e.printStackTrace();
        }

        // return something as response.

        response.setStatus(200);
        response.getWriter().write("Hello!");

        if (userResponse.statusCode() == 200 || userResponse.statusCode() == 201) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/addevent.jsp");
            dispatcher.forward(request, response);
        } else if (userResponse.statusCode() >= 400 && userResponse.statusCode() >= 499) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("email", JsonParser.parseString(request.getParameter("email")));
        jsonObject.add("password", JsonParser.parseString(request.getParameter("email")));

        HttpRequest userRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/mydiary/api/user/new"))
                .headers("Content-Type", "application/json") //might not be needed, needs to be tested
                .POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                .build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<Void> userResponse = null;
        try {
            userResponse = client.send(userRequest, HttpResponse.BodyHandlers.discarding());
        } catch (InterruptedException e) {
            System.out.println("Something went wrong with sending request...");
            e.printStackTrace();
        }

        // return something as response.
        response.setStatus(200);
        response.getWriter().write("Hello!");

        if(userResponse.statusCode() == 200 || userResponse.statusCode() == 201) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/addevent.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
        //dispatcher.forward(request, response);
    }

    public void addevent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/addevent.jsp");
        //dispatcher.forward(request, response);
    }

    public void usabilitygraph(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/usabilitygraph.jsp");
        //dispatcher.forward(request, response);
    }

    public void usersettings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/usersettings.jsp");
        //dispatcher.forward(request, response);
    }
    public boolean UserVerification(HttpServletRequest request, HttpServletResponse response)
    {
        return true;
    }
}