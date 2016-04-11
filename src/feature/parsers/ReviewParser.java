package feature.parsers;/* Authored by Kushagra on 4/10/2016. */

import model.Review;
import model.ReviewSet;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Date;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReviewParser {


    /**
     * Main method for testing purposes, can be removed
     **/
    public static void main(String[] args) throws IOException, ParseException {

        Map<Integer, Instance> instanceMap = Instance.getMap_Instances();

        Map<Integer, ReviewSet> reviewSetMapper = buildReviewSetMap(instanceMap);

        storetoFILE(reviewSetMapper);
    }

    private static void storetoFILE(Map<Integer, ReviewSet> reviewSetMapper) {
        // TODO: after complete testing for fasterIO
    }

    private static Map<Integer, ReviewSet> buildReviewSetMap(Map<Integer, Instance> instanceMap) throws IOException,
            ParseException {

        Map<Integer, ReviewSet> map = new HashMap<>();

        String filePath = "data/yelp_academic_dataset_review.json";
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line;
        int index = 1;
        int totalLines = countLines(filePath);
        while ((line = br.readLine()) != null) {

            Review reviewEntry = getReviewEntry(line);
            int serialID = locateSerialID(reviewEntry, instanceMap);

            insertToMap(serialID, reviewEntry, map);

            System.out.println(index++ + " / " + totalLines);
        }

        br.close();

        PopulateEntriesInMap(map);
        return map;
    }

    private static int countLines(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        int lines = 0;
        while (reader.readLine() != null) lines++;

        reader.close();
        return lines;
    }

    private static void PopulateEntriesInMap(Map<Integer, ReviewSet> map) {

        for (int serialID : map.keySet()) {
            ReviewSet rs = map.get(serialID);
            ReviewSet.FillEntriesInReviewSet(rs);
        }
    }

    private static void insertToMap(int serialID, Review reviewEntry, Map<Integer, ReviewSet> map) {

        if (map.containsKey(serialID)) {
            ReviewSet reviewSet = map.get(serialID);
            reviewSet.getReviewSet().add(reviewEntry);
        } else {
            ReviewSet reviewSet = new ReviewSet();
            reviewSet.getReviewSet().add(reviewEntry);
        }
    }

    private static int locateSerialID(Review reviewEntry, Map<Integer, Instance> instanceMap) {
        Date currentDate = reviewEntry.getDate();
        String yelpID = reviewEntry.getYelpID();

        for (int serialID : instanceMap.keySet()) {

            Instance instance = instanceMap.get(serialID);
            Date startDate = instance.startDate;
            Date endDate = instance.endDate;

            // match restaurant
            if (instance.yelpID.equals(yelpID)) {
                // match dates
                if (Date.liesInBetween(startDate, endDate, currentDate)) {
                    return serialID;
                }
            }
        }

        return 0;
    }


    // Parse JSON line / CSV
    private static Review getReviewEntry(String line) throws ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(line);

        // get businessID
        JSONObject jsonStringObject = (JSONObject) obj;

        String businessID = getBusinessID(jsonStringObject);
        int userRating = parseUserRating(jsonStringObject);
        Date date = parseDate(jsonStringObject);
        String reviewText = parseReviewText(jsonStringObject);

        Review reviewEntry = new Review();
        reviewEntry.setDate(date);
        reviewEntry.setText(reviewText);
        reviewEntry.setUserRating(userRating);
        reviewEntry.setYelpID(businessID);

        return reviewEntry;
    }


    private static String parseReviewText(JSONObject jsonStringObject) {
        return (String) jsonStringObject.get("text");
    }

    private static Date parseDate(JSONObject jsonStringObject) {

        String dateString = (String) jsonStringObject.get("date");
        return new Date(dateString);
    }

    private static int parseUserRating(JSONObject jsonStringObject) {
        return (int) (long) jsonStringObject.get("stars");
    }

    private static String getBusinessID(JSONObject jsonStringObject) {
        return (String) jsonStringObject.get("business_id");
    }

    private static HashMap<String, String> parseJSONForReviewCount(String jsonString) throws ParseException {
        HashMap<String, String> map = new HashMap<>();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonString);

        // get businessID
        JSONObject businessID = (JSONObject) obj;
        System.out.println(businessID.get("business_id"));
        String businessIDValue = (String) businessID.get("business_id");
        map.put("business_id", businessIDValue);

        // get date
        map.put("date", "12-12-2012");

        return map;
    }
}
