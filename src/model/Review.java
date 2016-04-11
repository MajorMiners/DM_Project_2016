package model;/* Authored by Kushagra on 4/10/2016. */

import utils.Date;

public class Review {

    private String yelpID;

    private int userRating;
    private Date date;
    private String text;

    public Review() {
        // Empty Constructor
    }

    public Review(String yelpID, int userRating, String dateString, String text) {
        this.userRating = userRating;
        this.date = new Date(dateString);
        this.text = text;
        this.yelpID = yelpID;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getYelpID() {
        return yelpID;
    }

    public void setYelpID(String yelpID) {
        this.yelpID = yelpID;
    }
}
