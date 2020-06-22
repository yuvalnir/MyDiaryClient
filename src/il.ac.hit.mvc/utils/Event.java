package il.ac.hit.mvc.utils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.*;
import java.sql.Time;
import java.sql.Date;


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
    public Event(String email,long id, String title, String location, Time timeStart, Time timeEnd, java.sql.Date date, String note) {
        this.email = email;
        this.id = id;
        this.title = title;
        this.location = location;
        this.starts = timeStart;
        this.ends = timeEnd;
        this.date = date;
        this.note = note;

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

    @Override
    public String toString() {
        String retStr=
                "Email: "+getEmail()+" "
                +"Title: "+getTitle()+" "
                +" Location: "+getLocation()+" "
                +" Time starts: "+getStarts().toString()+" "
                +" Time ends: "+getEnds().toString()+" "
                +" ID: "+getId()+" "
                +" Date: "+getDate().toString()+" "
                +" Note: "+note+" ";
        return retStr;
    }
}

