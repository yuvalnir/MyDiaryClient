package il.ac.hit.mvc.Utilities;
import java.util.Date;

public class Event {
    private String Title;
    private String Location;
    private Double TimeStart;
    private Double TimeEnd;
    private int Id;
    private Date Date;
    private String Note;

    public Event(String title, String location, Double timeStart, Double timeEnd, int id, java.util.Date date, String note) {
        Title = title;
        Location = location;
        TimeStart = timeStart;
        TimeEnd = timeEnd;
        Id = id;
        Date = date;
        Note = note;
    }

    public String getLocation() { return Location; }

    public void setLocation(String location) { Location = location; }

    public Double getTimeStart() { return TimeStart; }

    public void setTimeStart(Double timeStart) { TimeStart = timeStart; }

    public Double getTimeEnd() { return TimeEnd; }

    public void setTimeEnd(Double timeEnd) { TimeEnd = timeEnd; }

    public String getNote() { return Note; }

    public void setNote(String note) { Note = note; }

    public String getTitle() { return Title; }

    public void setTitle(String title) { Title = title; }

    public int getId() { return Id; }

    public void setId(int id) { Id = id; }

    public java.util.Date getDate() { return Date; }

    public void setDate(java.util.Date date) { Date = date; }
}
