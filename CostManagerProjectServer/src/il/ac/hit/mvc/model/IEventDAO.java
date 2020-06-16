package il.ac.hit.mvc.model;
import il.ac.hit.mvc.Utilities.Event;
import il.ac.hit.mvc.Utilities.UserDetails;

import java.util.List;
public interface IEventDAO {
    void insertEvent(UserDetails userDetails, Event item) throws DAOException;
    void deleteEvent(UserDetails userDetails, Event item) throws DAOException;
    void deleteEvents(UserDetails userDetails, List<Event> eventsList) throws DAOException;
    Event getEvent(UserDetails userDetails, int id) throws DAOException;
    List<Event> getEvents(UserDetails userDetails) throws DAOException;
    Boolean createNewUser(UserDetails userDetails)throws DAOException;
    Boolean deleteUser(UserDetails userDetails)throws DAOException;

   boolean userVerification(UserDetails userDetails) throws DAOException;

}
