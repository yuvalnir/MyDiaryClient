package Utilities;

import java.util.Date;

public class Item {
    private String Title;
    private double Cost;
    private int Id;
    private Date Date;

    public Item(String title, double cost, int id, java.util.Date date) {
        Title = title;
        Cost = cost;
        Id = id;
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }
}
