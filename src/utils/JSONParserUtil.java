package utils;/* Authored by Kushagra on 4/11/2016. */

import org.json.simple.JSONObject;

public class JSONParserUtil {

    public static String parseReviewText(JSONObject jsonStringObject) {
        return (String) jsonStringObject.get("text");
    }

    public static Date parseDate(JSONObject jsonStringObject) {

        String dateString = (String) jsonStringObject.get("date");
        return new Date(dateString);
    }

    public static int parseUserRating(JSONObject jsonStringObject) {
        return (int) (long) jsonStringObject.get("stars");
    }

    public static String getBusinessID(JSONObject jsonStringObject) {
        return (String) jsonStringObject.get("business_id");
    }

    public static int parseReviewResponse(JSONObject jsonStringObject) {
        int funnyCount = parseVoteTypes(jsonStringObject, "funny");
        int usefulCount = parseVoteTypes(jsonStringObject, "useful");
        int coolCount = parseVoteTypes(jsonStringObject, "cool");

        return funnyCount + usefulCount + coolCount;
    }

    private static int parseVoteTypes(JSONObject jsonStringObject, String voteType) {
        JSONObject votes = (JSONObject) jsonStringObject.get("votes");

        return (int) (long) votes.get(voteType);
    }

}
