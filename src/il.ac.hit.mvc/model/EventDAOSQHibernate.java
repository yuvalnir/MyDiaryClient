package il.ac.hit.mvc.model;

import il.ac.hit.mvc.controller.MVCException;
import il.ac.hit.mvc.utils.Event;
import il.ac.hit.mvc.utils.UserDetails;

import java.util.List;

public class EventDAOSQHibernate implements IEventDAO {


    @Override
    public void insertEvent(UserDetails userDetails, Event item) throws DAOException {

    }

    @Override
    public void deleteEvent(UserDetails userDetails, Event item) throws DAOException {

    }

    @Override
    public void deleteEvents(UserDetails userDetails, List<Event> eventsList) throws DAOException {

    }

    @Override
    public Event getEvent(UserDetails userDetails, int id) throws DAOException {
        return null;
    }

    @Override
    public List<Event> getEvents(UserDetails userDetails) throws DAOException {
        return null;
    }

    @Override
    public Boolean createNewUser(UserDetails userDetails) throws DAOException {
        return null;
    }

    @Override
    public Boolean deleteUser(UserDetails userDetails) throws DAOException {
        return null;
    }

    @Override
    public boolean userVerification(UserDetails userDetails) throws DAOException {
        return false;
    }
}

