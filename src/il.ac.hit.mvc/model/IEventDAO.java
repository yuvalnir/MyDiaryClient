package il.ac.hit.mvc.model;

import il.ac.hit.mvc.utils.Event;
import il.ac.hit.mvc.utils.User;
import java.util.List;

public interface IEventDAO {

    void insertEvent(Event event) throws DAOException;
    void deleteEvent(Event event) throws DAOException;
    void deleteEvents(List<Event> eventsList) throws DAOException;
    Event getEvent(String email, long id) throws DAOException;
    List<Event> getEvents(String email) throws DAOException;


    boolean createNewUser(User user)throws DAOException;
    boolean deleteUser(User user)throws DAOException;
    boolean userVerification(User user) throws DAOException;
}
