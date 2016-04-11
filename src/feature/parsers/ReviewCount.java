package feature.parsers;/* Authored by Kushagra on 4/10/2016. */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReviewCount {


    /**
     * Main method for testing purposes, can be removed
     **/
    public static void main(String[] args) throws IOException, ParseException {

        Map<Integer, Instance> instanceMap = Instance.getMap_Instances();

        Map<Integer, Double> getMap_Feature_2_ReviewCount = buildReviewCountMap(instanceMap);
    }

    private static Map<Integer, Double> buildReviewCountMap(Map<Integer, Instance> instanceMap) throws IOException,
            ParseException {

        Map<Integer, Double> map = new HashMap<>();
        init(map, instanceMap);

        String filePath = "data/yelpdb/yelp_academic_dataset_business.json";
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = br.readLine()) != null) {

            HashMap<String, String> jsonMap = parseJSONForReviewCount(line);




            System.out.println(map.toString());
        }

        br.close();
        return null;
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


    private static void init(Map<Integer, Double> map, Map<Integer, Instance> instanceMap) {

        for (int serialID : instanceMap.keySet()) {

            Instance instance = instanceMap.get(serialID);

            map.put(serialID, 0d);
        }
    }
}
