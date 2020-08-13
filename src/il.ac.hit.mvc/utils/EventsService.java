package il.ac.hit.mvc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventsService {
    /**
     * provides several methods for the eventsController
     */
    private Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();

    public List<Event> fetchEvents(String email, String password) throws URISyntaxException {
        /**
         * gets user events from the DB returns List<Event>
         */
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("email", JsonParser.parseString(email));
        jsonObject.add("password", JsonParser.parseString(password));

        HttpRequest userRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/mydiary/api/event/get/all"))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                .build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> userEventsResponse = null;
        try {
            userEventsResponse = client.send(userRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(userEventsResponse.toString()); //remove later
            System.out.println(userEventsResponse.body()); //remove later
        } catch (InterruptedException | IOException e) {
            System.out.println("Something went wrong with sending request...");
            e.printStackTrace();
        }

        Type listType = new TypeToken<List<Event>>(){}.getType();
        List<Event> events = gson.fromJson(userEventsResponse.body(), listType);

        return events;
    }

    public Map<String, Integer> getEventsByLocation (List<Event> events) {
        /**
         * gets all the user events and returns a HashMap<String, Integer>
         * of the location and the number of times it appears in all the user events
         */

        HashMap<String, Integer> eventsLocationCounter = new HashMap<>();

        for (Event event : events) {
            String eventLocation = event.getLocation();
            if (eventsLocationCounter.containsKey(eventLocation)) {
                Integer locationCount = eventsLocationCounter.get(eventLocation);
                locationCount++;
                eventsLocationCounter.put(eventLocation, locationCount);
            } else {
                eventsLocationCounter.put(eventLocation, 1);
            }
        }

        return eventsLocationCounter;
    }
}
