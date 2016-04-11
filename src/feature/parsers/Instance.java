package feature.parsers;/* Authored by Kushagra on 4/10/2016. */

import model.AllViolationData;
import parser.AllViolationParser;
import utils.Date;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Instance {

    String businessID;
    String yelpID;
    Date startDate;
    Date endDate;

    public Instance(String businessID, Date startDate, Date endDate) {

        this.businessID = businessID;
        this.startDate = startDate;
        this.endDate = endDate;

        // TODO: use map to populate yelpID
    }


    public static Instance getInstance(AllViolationData entry, List<AllViolationData> list) {

        String businessID = entry.getRestaurantID();
        Date endDate = entry.getDate();
        Date startDate = calculateStartDate(entry, list);

        return new Instance(businessID, startDate, endDate);
    }

    private static Date calculateStartDate(AllViolationData entry, List<AllViolationData> list) {

        Date nearestEarlierDate = Date.getBaseDate();
        Date currentDate = entry.getDate();

        for (AllViolationData data : list) {

            String businessID = data.getRestaurantID();
            Date date = data.getDate();

            // if restaurantID matches
            if (entry.getRestaurantID().equals(businessID)) {

                // if the date is earlier
                if (Date.isEarlier(currentDate, date)) {

                    // update date, IF necessary
                    if (Date.isLater(nearestEarlierDate, date))
                        nearestEarlierDate = date;
                }
            }
        }

        // TODO: handle case for: earlier date does not exist >> DONE
        if (nearestEarlierDate.equals(Date.getBaseDate())) {
            nearestEarlierDate = Date.getSixMonthsEarlier(currentDate);
        }

        return nearestEarlierDate;
    }


    // TODO: results can be stored in a file for faster IO
    public static Map<Integer, Instance> getMap_Instances() throws IOException {
        Map<Integer, Instance> map = new HashMap<>();
        List<AllViolationData> list = AllViolationParser.readViolationData();

        int index = 1;
        for (AllViolationData data : list) {
            System.out.println(index++ + " / " + list.size());
            int serialID = data.getSerialID();
            Instance instance = getInstance(data, list);

            map.put(serialID, instance);
        }

        return map;
    }

    /** Main method for testing purposes, can be removed **/
    public static void main(String[] args) throws IOException {

        System.out.println("Start");
        Map<Integer, Instance> map = getMap_Instances();
        System.out.println("Done");
    }
}
