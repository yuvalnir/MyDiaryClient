package il.ac.hit.mvc.controller;

import com.google.gson.*;
import il.ac.hit.mvc.utils.Event;
import il.ac.hit.mvc.utils.EventsService;
import il.ac.hit.mvc.utils.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController {

    private EventsService eventsService = new EventsService();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
        JsonObject userJson = new JsonObject();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        userJson.add("email", JsonParser.parseString(email));
        userJson.add("password", JsonParser.parseString(password));

        HttpRequest userRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/mydiary/api/user/verify"))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(userJson.toString()))
                .build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<Void> userResponse = null;
        try {
            userResponse = client.send(userRequest, HttpResponse.BodyHandlers.discarding()); //might be HttpResponse.BodyHandlers.ofInputStream()
        } catch (InterruptedException e) {
            System.out.println("Something went wrong with sending request...");
            e.printStackTrace();
        }

        // redirecting to jsp page
        if (userResponse.statusCode() == 200) {
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("password", password);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/eventslist.jsp");
            request.setAttribute("events", eventsService.fetchEvents(email, password));
            dispatcher.forward(request, response);
        } else if (userResponse.statusCode() >= 400 && userResponse.statusCode() >= 499) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void signup(HttpServletRequest request, HttpServletResponse response) throws MVCException, ServletException, IOException, URISyntaxException {
        JsonObject userJson = new JsonObject();
        userJson.add("email", JsonParser.parseString(request.getParameter("email")));
        userJson.add("password", JsonParser.parseString(request.getParameter("password")));

        System.out.println("Email is " + request.getParameter("email")); //remove later
        System.out.println("Password is " + request.getParameter("password")); //remove later
        System.out.println(userJson.toString()); //remove later

        HttpRequest userRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/mydiary/api/user/new"))
                .headers("Content-Type", "application/json")
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

    public void usabilitygraph(HttpServletRequest request, HttpServletResponse response) throws MVCException, ServletException, IOException, URISyntaxException {

        String email = ((String) request.getSession().getAttribute("email"));
        String password = ((String) request.getSession().getAttribute("password"));

        List<Event> events = eventsService.fetchEvents(email, password);
        Map<String, Integer> locationList = eventsService.getEventsByLocation(events);

        request.setAttribute("locationList", locationList);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/usabilitygraph.jsp");
        dispatcher.forward(request, response);
    }

    public void deleteuser(HttpServletRequest request, HttpServletResponse response) throws MVCException, ServletException, IOException, URISyntaxException {

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("email", JsonParser.parseString((String) request.getSession().getAttribute("email")));
        jsonObject.add("password", JsonParser.parseString((String) request.getSession().getAttribute("password")));


        HttpRequest userRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/mydiary/api/user/delete"))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                .build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<Void> userResponse = null;
        try {
            userResponse = client.send(userRequest, HttpResponse.BodyHandlers.discarding()); //might be HttpResponse.BodyHandlers.ofInputStream()
        } catch (InterruptedException e) {
            System.out.println("Something went wrong with sending request...");
            e.printStackTrace();
        }

        if(userResponse.statusCode() == 200) {
            //deleting logged in user from the session
            HttpSession session = request.getSession(false);
            if(session!=null)
                session.invalidate();
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/home.jsp");
            dispatcher.forward(request, response);
        } else {
            //in case delete user didn't succeed
            System.out.println("Something went wrong with deleting the user...");
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws MVCException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session!=null)
            session.invalidate();
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/home.jsp");
        dispatcher.forward(request, response);
    }
}