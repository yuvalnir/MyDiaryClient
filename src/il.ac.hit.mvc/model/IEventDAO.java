package il.ac.hit.mvc.model;

import il.ac.hit.mvc.utils.Event;
import il.ac.hit.mvc.utils.User;
import java.util.List;

public interface IEventDAO {

    void insertEvent(User user, Event item) throws MVCException;
    void deleteEvent(User user, Event item) throws MVCException;
    void deleteEvents(User user, List<Event> eventsList) throws MVCException;
    Event getEvent(User user, int id) throws MVCException;
    List<Event> getEvents(User user) throws MVCException;
    boolean createNewUser(User user)throws MVCException;
    boolean deleteUser(User user)throws MVCException;
    boolean userVerification(User user) throws MVCException;
}
