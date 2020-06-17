package il.ac.hit.mvc.model;

import il.ac.hit.mvc.controller.MVCException;
import il.ac.hit.mvc.utils.Event;
import il.ac.hit.mvc.utils.User;
import java.util.List;

public class EventDAOSQHibernate implements MVCException {

    @Override
    public void insertEvent(User user, Event item) throws MVCException {

    }

    @Override
    public void deleteEvent(User user, Event item) throws MVCException {

    }

    @Override
    public void deleteEvents(User user, List<Event> eventsList) throws MVCException {

    }

    @Override
    public Event getEvent(User user, int id) throws MVCException {
        return null;
    }

    @Override
    public List<Event> getEvents(User user) throws MVCException {
        return null;
    }

    @Override
    public boolean createNewUser(User user) throws MVCException {
        return false;
    }

    @Override
    public boolean deleteUser(User user) throws MVCException {
        return false;
    }

    @Override
    public boolean userVerification(User user) throws MVCException {
        return false;
    }
}

