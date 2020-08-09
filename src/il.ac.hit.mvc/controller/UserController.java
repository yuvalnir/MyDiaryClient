package controller;

import com.google.gson.*;
import il.ac.hit.mvc.utils.Event;
import il.ac.hit.mvc.utils.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class UserController {


    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
        JsonObject userJson = new JsonObject();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        userJson.add("email", JsonParser.parseString(email));
        userJson.add("password", JsonParser.parseString(password));

        System.out.println("Email is " + request.getParameter("email")); //remove later
        System.out.println("Password is " + request.getParameter("password")); //remove later
        System.out.println(userJson.toString()); //remove later

        HttpRequest userRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/mydiary/api/user/verify"))
                .headers("Content-Type", "application/json") //might not be needed, needs to be tested
                .POST(HttpRequest.BodyPublishers.ofString(userJson.toString()))
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

        // redirecting to jsp page
        if (userResponse.statusCode() == 200) {

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/eventslist.jsp");
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("password", password);
            request.setAttribute("events", refresheventslist(request, response));
            dispatcher.forward(request, response);
        } else if (userResponse.statusCode() >= 400 && userResponse.statusCode() >= 499) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
        JsonObject userJson = new JsonObject();
        userJson.add("email", JsonParser.parseString(request.getParameter("email")));
        userJson.add("password", JsonParser.parseString(request.getParameter("password")));

        System.out.println("Email is " + request.getParameter("email")); //remove later
        System.out.println("Password is " + request.getParameter("password")); //remove later
        System.out.println(userJson.toString()); //remove later

        HttpRequest userRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/mydiary/api/user/new"))
                .headers("Content-Type", "application/json") //might not be needed, needs to be tested
                .POST(HttpRequest.BodyPublishers.ofString(userJson.toString()))
                .build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<Void> userResponse = null;
        try {
            userResponse = client.send(userRequest, HttpResponse.BodyHandlers.discarding());
            System.out.println(userResponse.toString()); //remove later
            System.out.println(userResponse.body()); //remove later
        } catch (InterruptedException e) {
            System.out.println("Something went wrong with sending request...");
            e.printStackTrace();
        }

        // redirecting to jsp page
        if(userResponse.statusCode() == 201) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/addevent.jsp");
            dispatcher.forward(request, response);
        } else if(userResponse.statusCode() == 200) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/addevent.jsp");
            dispatcher.forward(request, response);
        } else if(userResponse.statusCode() >= 400 && userResponse.statusCode() <= 499) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void addevent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {

        JsonObject jsonObject = new JsonObject();
        JsonArray jsonEvent = new JsonArray();

        jsonEvent.add(JsonParser.parseString((String) request.getSession().getAttribute("email")));
        jsonEvent.add(1); //event id
        jsonEvent.add(request.getParameter("title"));
        jsonEvent.add(request.getParameter("location"));
        jsonEvent.add(request.getParameter("timeStart") + ":00");
        jsonEvent.add(request.getParameter("timeEnd") + ":00");
        jsonEvent.add(request.getParameter("date"));
        jsonEvent.add(request.getParameter("note"));

        jsonObject.add("email", JsonParser.parseString((String) request.getSession().getAttribute("email")));
        jsonObject.add("password", JsonParser.parseString((String) request.getSession().getAttribute("password")));
        jsonObject.add("event", jsonEvent);

        System.out.println("Add Event String: "+ jsonObject.toString()); //remove later

        HttpRequest userRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/mydiary/api/event/new"))
                .headers("Content-Type", "application/json") //might not be needed, needs to be tested
                .POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                .build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<Void> userResponse = null;
        try {
            userResponse = client.send(userRequest, HttpResponse.BodyHandlers.discarding());
            System.out.println(userResponse.toString()); //remove later
            System.out.println(userResponse.body()); //remove later
        } catch (InterruptedException e) {
            System.out.println("Something went wrong with sending request...");
            e.printStackTrace();
        }

        // redirecting to jsp page
        if(userResponse.statusCode() == 200) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/eventslist.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    public String refresheventslist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("email", JsonParser.parseString((String) request.getSession().getAttribute("email")));
        jsonObject.add("password", JsonParser.parseString((String) request.getSession().getAttribute("password")));

        HttpRequest userRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/mydiary/api/event/get/all"))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                .build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> userResponse = null;
        try {
            userResponse = client.send(userRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(userResponse.toString()); //remove later
            System.out.println(userResponse.body()); //remove later
        } catch (InterruptedException e) {
            System.out.println("Something went wrong with sending request...");
            e.printStackTrace();
        }

        // redirecting to jsp page
        if(userResponse.statusCode() == 200) {
            return userResponse.body();
            //RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/eventslist.jsp");
            //dispatcher.forward(request, response);
        } else {
            return null;
            //RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/error.jsp");
            //dispatcher.forward(request, response);
        }
    }

    public void usabilitygraph(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/usabilitygraph.jsp");
        //dispatcher.forward(request, response);
    }

    public void usersettings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/usersettings.jsp");
        //dispatcher.forward(request, response);
    }
}