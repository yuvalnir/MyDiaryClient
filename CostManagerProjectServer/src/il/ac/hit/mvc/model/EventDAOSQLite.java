package il.ac.hit.mvc.model;
import il.ac.hit.mvc.Utilities.UserDetails;
import il.ac.hit.mvc.controller.MVCException;
import il.ac.hit.mvc.Utilities.Event;

import java.util.List;
public class EventDAOSQLite implements IEventDAO {

    @Override
    public void insertEvent(UserDetails userDetails, Event item) throws MVCException {
        //TODO

    }

    @Override
    public void deleteEvent(UserDetails userDetails, Event item) throws MVCException {
//TODO
    }

    @Override
    public void deleteEvents(UserDetails userDetails, List<Event> eventsList) throws MVCException {
//TODO
    }

    @Override
    public Event getEvent(UserDetails userDetails, int id) throws MVCException {
        return null;
        //TODO
    }

    @Override
    public List<Event> getEvents(UserDetails userDetails) throws MVCException {
        return null;
        //TODO
    }

    @Override
    public Boolean createNewUser(UserDetails userDetails) throws DAOException {
        return null;
        //TODO
    }

    @Override
    public boolean userVerification(UserDetails userDetails) throws DAOException {
        return false;
        //TODO
    }
}

