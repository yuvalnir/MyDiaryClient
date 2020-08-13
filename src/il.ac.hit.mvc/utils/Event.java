package il.ac.hit.mvc.utils;

import java.sql.Time;
import java.sql.Date;

public class Event {
    /**
     *Contains an event that is suitable for Hibernate. all members are private hence we use setters and getters
     * */
    private long id;
    private String email;
    private String title;
    private String location;
    private String starts;
    private String ends;
    private Date date;
    private String note;

    //by default while working with hibernate you must have a public cot'r without param's
    public Event() {
        /**
         * default Cto'r
         *
         * */
    }

    public Event(/*@NotNull*/ String email, Long id, /*@NotNull*/ String title, String location, String timeStart, String timeEnd, Date date, String note) {
        /**
         *  overloading Cto'r.
         * */
        setEmail(email);
        setId(id);
        setTitle(title);
        setLocation(location);
        setStarts(timeStart);
        setEnds(timeEnd);
        setDate(date);
        setNote(note);
    }

    public String getTitle() {
        /**gets the event title as a String object, cant be Null*/
        return title;
    }

    public void setTitle(/*@NotNull*/ String title) {
        /**
         * sets the event title as a String object, cant be Null
         * */
        this.title = title;
    }

    public String getLocation() {
        /**
         * returns the event location as String
         * */
        return location;
    }

    public void setLocation(String location) {
        /**
         * sets the event Location as a String
         */
        this.location = location;
    }

    public long getId() {
        /**
         * get the event id, id can not be Null.
         */
        return id;
    }

    public void setId(/*@NotNull*/ long id) {
        /**
         * sets the id.
         */
        this.id = id;
    }

    public Date getDate() {
        /**
         * gets the event date as import java.util.Date object
         */
        return date;
    }

    public void setDate(Date date) {
        /**
         * sets the event date as import java.util.Date object
         */
        this.date = date;
    }

    public String getNote() {
        /**
         * gets the event note as a String object
         */
        return note;
    }

    public void setNote(String note) {
        /**
         * sets the event note as a String object
         */
        this.note = note;
    }

    public String getStarts() {
        /**
         * gets the event time as import java.util.Date object
         */
        return starts;
    }

    public String getEnds() {
        /**
         * gets the event time as import java.util.Date object
         */
        return ends;
    }

    public void setEnds(String ends) {
        /**
         * sets the event time as import java.util.Date object
         */
        this.ends = ends;
    }

    public void setStarts(String starts) {
        /**
         * sets the event time as import java.util.Date object
         */
        this.starts = starts;
    }

    public String getEmail() {
        /**
         * gets the event User email as a String object
         */
        return email;

    }

    public void setEmail(/*@NotNull*/ String email) {
        /**
         * sets the event User email as a String object. cant be null.
         */
        this.email = email;
        /*no need to check the email because we checked it in the "user" class//*/
    }

    @Override
    public String toString() {
        /**
         * return all the private members as {Member's name}: {Member's value} {next member's name}: {member's value} etc
         */
        return "Email: "+getEmail()+" "
                +"Title: "+getTitle()+" "
                +" Location: "+getLocation()+" "
                +" Time starts: "+getStarts()+" "
                +" Time ends: "+getEnds()+" "
                +" ID: "+getId()+" "
                +" Date: "+getDate().toString()+" "
                +" Note: "+note+" ";
    }
}

