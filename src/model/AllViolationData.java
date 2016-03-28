package model;/* Authored by Kushagra on 3/28/2016. */

/**
 * Models data from AllViolations.csv
 **/

public class AllViolationData {

    private String date;
    private String restaurantID;
    private ViolationEntry violationEntry;

    public AllViolationData() {
        date = "";
        restaurantID = "";
        this.violationEntry = new ViolationEntry();
    }

    public AllViolationData(String date, String restID, ViolationEntry ve) {
        this.date = date;
        this.restaurantID = restID;
        this.violationEntry = new ViolationEntry();
    }

    public AllViolationData(String date, String restID, int v1, int v2, int v3) {
        this.date = date;
        this.restaurantID = restID;
        this.violationEntry.setMinorViolationCount(v1);
        this.violationEntry.setMajorViolationCount(v2);
        this.violationEntry.setSevereViolationCount(v3);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public ViolationEntry getViolationEntry() {
        return violationEntry;
    }

    @Override
    public String toString() {
        String newline = "\n";
        String dateString = "Date: " + this.date;
        String restIDString = "RestaurantID: " + restaurantID;
        String veString = "Violation Entries: " + violationEntry;

        return dateString + newline + restIDString + newline + veString + newline;
    }
}

