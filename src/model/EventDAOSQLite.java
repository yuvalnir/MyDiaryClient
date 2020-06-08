package model;

import Utilities.MyDiaryException;
import Utilities.Event;

import java.util.List;

public class EventDAOSQLite implements EventDAO {
    @Override
    public void insertEvent(Event event) throws MyDiaryException {

    }

    @Override
    public Event getEvent(int id) throws MyDiaryException {
        return null;
    }

    @Override
    public List<Event> getEvents() throws MyDiaryException {
        return null;
    }
}
