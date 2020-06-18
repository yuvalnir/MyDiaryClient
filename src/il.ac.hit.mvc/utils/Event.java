package il.ac.hit.mvc.utils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.*;
import java.sql.Time;
import java.util.Date;


public class Event {
    private long id;
    private String email;
    private String title;
    private String location;
    private Time starts;
    private Time ends;
    private Date date;
    private String note;

    //by default while working with hibernate you must have a public cot'r without param's
    public Event() {

    }

/*    @Override
    public String toString() {
        return  "Event ID: "+id+ "Email: "+email+"Title: "+title+"Location: "+location+"time starts: "+starts.toString()+"time it ends: "+ends.toString()
         +"Date: "+ date.toString()+"Note: "+note;
    }*/

    //overLoading
    public Event(String email, String title, String location, Time timeStart, Time timeEnd, long id, java.util.Date date, String note) {
        this.title = title;
        this.location = location;
        this.starts = timeStart;
        this.ends = timeEnd;
        this.id = id;
        this.date = date;
        this.note = note;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Time getStarts() {
        return starts;
    }

    public void setStarts(Time starts) {
        this.starts = starts;
    }

    public Time getEnds() {
        return ends;
    }

    public void setEnds(Time ends) {
        this.ends = ends;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

