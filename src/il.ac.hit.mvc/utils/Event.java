package il.ac.hit.mvc.utils;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


public class Event {
    private String title;
    private String location;
    private Double starts;
    private Double ends;
    private int id;
    private Date date;
    private String note;
    private String email;

    //by default while working with hibernate you must have a public cot'r without param's
    public Event() {

    }

    //overLoading


    public Event(String email, String title, String location, Double timeStart, Double timeEnd, int id, java.util.Date date, String note) {
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

    public Double getStarts() {
        return starts;
    }

    public void setStarts(Double starts) {
        this.starts = starts;
    }

    public Double getEnds() {
        return ends;
    }

    public void setEnds(Double ends) {
        this.ends = ends;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

