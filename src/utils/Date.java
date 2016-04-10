package utils;/* Authored by Kushagra on 4/10/2016. */

public class Date {

    private int dd;
    private int mm;
    private int yyyy;

    public Date() {

    }

    public Date(String dateString) {

        String[] tokens = dateString.split("-");

        this.yyyy = Integer.parseInt(tokens[0]);
        this.mm = Integer.parseInt(tokens[1]);
        this.dd = Integer.parseInt(tokens[2]);
    }

    public int getDay() {
        return dd;
    }

    public void setDay(int dd) {
        this.dd = dd;
    }

    public int getMonth() {
        return mm;
    }

    public void setMonth(int mm) {
        this.mm = mm;
    }

    public int getYear() {
        return yyyy;
    }

    public void setYear(int yyyy) {
        this.yyyy = yyyy;
    }

    public boolean isEqual(String ds1, String ds2) {
        return ds1.equals(ds2);
    }

    @Override
    public String toString() {
        String ddString = String.valueOf(this.dd);
        String mmString = String.valueOf(this.mm);
        String yyString = String.valueOf(this.yyyy);

        String sep = "-";

        return ddString + sep + mmString + sep + yyString;
    }

    public static Date parseAsDate(String dateString) {
        return new Date(dateString);
    }

    // checks of ds2 < ds1
    private static boolean isEarlier(String ds1, String ds2) {
        Date d1 = new Date(ds1);
        Date d2 = new Date(ds2);

        if (d2.getYear() < d1.getYear()) return true;
        if (d2.getMonth() < d2.getMonth()) return true;
        if (d2.getDay() <= d2.getMonth()) return true;

        return false;
    }

    // checks of ds2 > ds1
    private static boolean isLater(String ds1, String ds2) {
        Date d1 = new Date(ds1);
        Date d2 = new Date(ds2);

        if (d2.getYear() > d1.getYear()) return true;
        if (d2.getMonth() > d2.getMonth()) return true;
        if (d2.getDay() >= d2.getMonth()) return true;

        return false;
    }

    // checks if currentDate lies in between startDate and endDate
    public static boolean liesInBetween(String startDate, String endDate, String currentDate) {

        return (isEarlier(endDate, currentDate) && isLater(startDate, currentDate));
    }

}
