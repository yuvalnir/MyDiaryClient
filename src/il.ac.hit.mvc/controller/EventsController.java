package il.ac.hit.mvc.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import il.ac.hit.mvc.utils.Event;
import il.ac.hit.mvc.utils.EventsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class EventsController {

    private EventsService eventsService = new EventsService();

    public void eventslist(HttpServletRequest request, HttpServletResponse response) throws MVCException, ServletException, IOException, URISyntaxException {
        String email = ((String) request.getSession().getAttribute("email"));
        String password = ((String) request.getSession().getAttribute("password"));

        List<Event> events = eventsService.fetchEvents(email, password);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/eventslist.jsp");
        request.setAttribute("events", events);
        dispatcher.forward(request, response);
    }

    public void deleteevent(HttpServletRequest request, HttpServletResponse response) throws MVCException, ServletException, IOException, URISyntaxException {

        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        jsonArray.add(request.getParameter("eventid"));

        jsonObject.add("email", JsonParser.parseString((String) request.getSession().getAttribute("email")));
        jsonObject.add("password", JsonParser.parseString((String) request.getSession().getAttribute("password")));
        jsonObject.add("eventid", jsonArray);

        HttpRequest userRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/mydiary/api/event/delete"))
                .headers("Content-Type", "application/json")
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

        if(userResponse.statusCode() == 200) {
            //in case delete event worked
            String email = (String) request.getSession().getAttribute("email");
            String password = (String) request.getSession().getAttribute("password");
            List<Event> events = eventsService.fetchEvents(email, password);
            request.setAttribute("events", events);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/eventslist.jsp");
            dispatcher.forward(request, response);
        } else {
            //in case delete event didn't succeed
            System.out.println("Something went wrong with deleting the user...");
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void updateevent(HttpServletRequest request, HttpServletResponse response) throws MVCException, ServletException, IOException, URISyntaxException {

        JsonObject jsonObject = new JsonObject();
        JsonArray jsonEvent = new JsonArray();

        jsonEvent.add(JsonParser.parseString((String) request.getSession().getAttribute("email")));
        jsonEvent.add("eventid");
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

        HttpRequest userRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/mydiary/api/event/update"))
                .headers("Content-Type", "application/json")
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
            String email = (String) request.getSession().getAttribute("email");
            String password = (String) request.getSession().getAttribute("password");
            List<Event> events = eventsService.fetchEvents(email, password);
            request.setAttribute("events", events);
            //response.sendRedirect("/MyDiary/views/eventslist.jsp");
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/eventslist.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
        }
    }

}