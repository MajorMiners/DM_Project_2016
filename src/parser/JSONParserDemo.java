package parser;
/* Authored by Kushagra on 3/28/2016. */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Date;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class JSONParserDemo {
    public static void main(String[] args) throws IOException, ParseException {

        String filePath = "data/yelp_academic_dataset_review.json";
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line;
        int linecount = 0;
        while ((line = br.readLine()) != null) {

            linecount++;

            if (linecount > 1) {
                System.out.println(line);

                String jsonString = line;
                HashMap<String, String> map = parseJSON(jsonString);

//                System.out.println(map.toString());
            }
        }

        br.close();
    }

    private static HashMap<String, String> parseJSON(String line) throws ParseException {
        HashMap<String, String> map = new HashMap<>();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(line);

        // get businessID
        JSONObject jsonStringObject = (JSONObject) obj;

        String businessID = getBusinessID(jsonStringObject);
        int userRating = parseUserRating(jsonStringObject);
        Date date = parseDate(jsonStringObject);
        String reviewText = parseReviewText(jsonStringObject);
        int reviewResponseCount = parseReviewResponse(jsonStringObject);

        return map;
    }

    private static int parseReviewResponse(JSONObject jsonStringObject) {
        int funnyCount = parseVoteTypes(jsonStringObject, "funny");
        int usefulCount = parseVoteTypes(jsonStringObject, "useful");
        int coolCount = parseVoteTypes(jsonStringObject, "cool");

        return funnyCount + usefulCount + coolCount;
    }

    private static int parseVoteTypes(JSONObject jsonStringObject, String voteType) {
        JSONObject votes = (JSONObject) jsonStringObject.get("votes");

        return (int) (long) votes.get(voteType);
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
}
