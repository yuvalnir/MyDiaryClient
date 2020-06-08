package model;

import Utilities.MyDiaryException;
import Utilities.Event;

import java.util.List;

public interface EventDAO {
    void insertEvent(Event item) throws MyDiaryException;
    Event getEvent(int id) throws MyDiaryException;
    List<Event> getEvents() throws MyDiaryException;

}
