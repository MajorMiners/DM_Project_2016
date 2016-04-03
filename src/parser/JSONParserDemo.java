package parser;
/* Authored by Kushagra on 3/28/2016. */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class JSONParserDemo {
    public static void main(String[] args) throws IOException, ParseException {

        String filePath = "data/yelpdb/yelp_academic_dataset_business.json";
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line;
        int linecount = 0;
        while ((line = br.readLine()) != null) {

            linecount++;

            if (linecount > 1) {
                System.out.println(line);

                String jsonString = line;
                HashMap<String, String> map = parseJSON(jsonString);

                System.out.println(map.toString());
            }
        }

        br.close();
    }

    private static HashMap<String, String> parseJSON(String jsonString) throws ParseException {
        HashMap<String, String> map = new HashMap<>();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonString);

        // get businessID
        JSONObject businessID = (JSONObject)obj;
        System.out.println(businessID.get("business_id"));
        String businessIDValue = (String)businessID.get("business_id");
        return map;
    }
}
