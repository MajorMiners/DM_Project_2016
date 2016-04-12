package parser;
/* Authored by Kushagra on 3/28/2016. */

import model.BusinessSet;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Date;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

@SuppressWarnings("Duplicates")
public class JSONParserDemo {
    public static void main(String[] args) throws IOException, ParseException {

        String filePath = "data/yelp_academic_dataset_business.json";
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line;
        int linecount = 0;
        while ((line = br.readLine()) != null) {

            linecount++;

            if (linecount > 1) {
                System.out.println(line);

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(line);
                JSONObject jsonStringObject = (JSONObject) obj;
//
////                JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
////                JSONObject goodFor = (JSONObject) attributes.get("Good For");
////
////                boolean dessert = (boolean) goodFor.get("dessert");
//
//                JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
//                boolean alcohol =  (boolean) attributes.get("Alcohol");
//
//                System.out.println(alcohol);



                BusinessSet bs = parseBusinessSet(jsonStringObject);






//                String jsonString = line;
//                HashMap<String, String> map = parseJSON(jsonString);

//                System.out.println(map.toString());

                System.out.println("End");

            }
        }

        br.close();
    }


    private static BusinessSet parseBusinessSet(JSONObject jsonStringObject) {
        BusinessSet businessSet = new BusinessSet();

        businessSet.setAlcoholic(parseAlcoholic(jsonStringObject));
        businessSet.setWaiterService(parseWaiterService(jsonStringObject));
        businessSet.setValetParking(parseValetParking(jsonStringObject));
        businessSet.setRomantic(parseRomantic(jsonStringObject));
        businessSet.setIntimate(parseIntimate(jsonStringObject));
        businessSet.setTouristy(parseTouristy(jsonStringObject));
        businessSet.setHipster(parseHipster(jsonStringObject));
        businessSet.setUpscale(parseUpscale(jsonStringObject));
        businessSet.setDeliveryAvailable(parseDeliveryOption(jsonStringObject));
        businessSet.setGoodForDessert(parseDessert(jsonStringObject));
        businessSet.setLatenight(parseLateNight(jsonStringObject));
        businessSet.setPriceRange(parsePriceRange(jsonStringObject));
        businessSet.setStars(parseStars(jsonStringObject));
        businessSet.setBusinessType(parseBusinessType(jsonStringObject));
        businessSet.setNoiseLevel(parseNoiseLevel(jsonStringObject));

        return businessSet;
    }

    private static String parseNoiseLevel(JSONObject jsonStringObject) {
        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
        return (String) attributes.get("Noise Level");
    }

    private static String parseBusinessType(JSONObject jsonStringObject) {
        return (String) jsonStringObject.get("type");
    }

    private static int parseStars(JSONObject jsonStringObject) {
        return (int) (double) jsonStringObject.get("stars");
    }

    private static int parsePriceRange(JSONObject jsonStringObject) {
        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
        return (int) (long) attributes.get("Price Range");
    }

    private static boolean parseLateNight(JSONObject jsonStringObject) {

        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
        JSONObject goodFor = (JSONObject) attributes.get("Good For");

        return (boolean) goodFor.get("latenight");
    }

    private static boolean parseDessert(JSONObject jsonStringObject) {

        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
        JSONObject goodFor = (JSONObject) attributes.get("Good For");

        return (boolean) goodFor.get("dessert");
    }

    private static boolean parseDeliveryOption(JSONObject jsonStringObject) {

        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");

        if (attributes.get("Delivery") == null)
            return false;

        return (boolean) attributes.get("Delivery");
    }

    private static boolean parseUpscale(JSONObject jsonStringObject) {
        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
        JSONObject ambience = (JSONObject) attributes.get("Ambience");

        return (boolean) ambience.get("upscale");
    }

    private static boolean parseHipster(JSONObject jsonStringObject) {
        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
        JSONObject ambience = (JSONObject) attributes.get("Ambience");

        return (boolean) ambience.get("hipster");
    }

    private static boolean parseTouristy(JSONObject jsonStringObject) {
        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
        JSONObject ambience = (JSONObject) attributes.get("Ambience");

        return (boolean) ambience.get("touristy");
    }

    private static boolean parseIntimate(JSONObject jsonStringObject) {
        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
        JSONObject ambience = (JSONObject) attributes.get("Ambience");

        return (boolean) ambience.get("intimate");
    }

    private static boolean parseRomantic(JSONObject jsonStringObject) {

        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
        JSONObject ambience = (JSONObject) attributes.get("Ambience");

        return (boolean) ambience.get("romantic");
    }

    private static boolean parseValetParking(JSONObject jsonStringObject) {
        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
        JSONObject parking = (JSONObject) attributes.get("Parking");

        return (boolean) parking.get("valet");
    }

    private static boolean parseWaiterService(JSONObject jsonStringObject) {
        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
        return (boolean) attributes.get("Waiter Service");
    }

    private static boolean parseAlcoholic(JSONObject jsonStringObject) {
        JSONObject attributes = (JSONObject) jsonStringObject.get("attributes");
        if (attributes.get("Alcohol") == null) {
            return false;
        }
        return (boolean) attributes.get("Alcohol");
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
