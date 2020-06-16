package il.ac.hit.mvc.Utilities;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Event {
    private String title;
    private String location;
    private Double timeStart;
    private Double timeEnd;
    private int id;
    private Date date;
    private String note;
    private String email;

    //by default while working with hibernate you must have a public cot'r without param's
    public Event()
    {

    }
    //overLoading
    public Event(String email, String title, String location, Double timeStart, Double timeEnd, int id, java.util.Date date, String note) {
        this.title = title;
        this.location = location;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.id = id;
        this.date = date;
        this.note = note;
        this.email=email;
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

    public Double getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Double timeStart) {
        this.timeStart = timeStart;
    }

    public Double getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Double timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Id
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
