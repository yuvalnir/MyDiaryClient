package il.ac.hit.mvc.controller;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import il.ac.hit.mvc.utils.Event;
import il.ac.hit.mvc.utils.EventsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class EventsController {

    private EventsService eventsService = new EventsService();

    public EventsController() {}

    public void events(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
        String email = ((String) request.getSession().getAttribute("email"));
        String password = ((String) request.getSession().getAttribute("password"));

        List<Event> events = eventsService.fetchEvents(email, password);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/eventslist.jsp");
        request.setAttribute("events", events);
        dispatcher.forward(request, response);
    }

}