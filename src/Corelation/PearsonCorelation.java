package Corelation;

import model.AllViolationData;
import model.ReviewData;
import model.ViolationEntry;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import parser.AllViolationParser;
import parser.ReviewParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PearsonCorelation {

    public static void main(String[] args) throws IOException {

        AllViolationParser violationParser = new AllViolationParser();
        List<AllViolationData> violationList = new ArrayList<AllViolationData>();
        violationList = AllViolationParser.readViolationData();

        ReviewParser reviewParser = new ReviewParser();

        System.out.print("Calculating review lists...");
        List<ReviewData> reviewList = ReviewParser.readReviews();
        System.out.println("\t\tDone");

        System.out.println("Reviews length: " + reviewList.size());

        double[] penaltyScores = new double[violationList.size()];
        double[] reviewLength = new double[violationList.size()];
        double[] reviewStars = new double[violationList.size()];

        // cached map to reference reviews with restaurantID
        Map<String, List<ReviewData>> restaurantIDReviewMap = buildMap(violationList, reviewList);

        int counter = 0;
        System.out.print("Calculating features... ");
        for (AllViolationData violation : violationList) {

            String restaurentId = violation.getRestaurantID();

//            List<ReviewData> reviewDataList = getReviewForRestaurentID(restaurentId, reviewList);
            List<ReviewData> reviewDataList = restaurantIDReviewMap.get(restaurentId);      // using map

            // fast
            ViolationEntry entry = violation.getViolationEntry();

            // fast
            double penaltyScore = entry.calculatePenaltyScore(entry);
            penaltyScores[counter] = penaltyScore;

            // fast
            double reviewlength = getReviewLength(reviewDataList);
            reviewLength[counter] = reviewlength;

            double avgStars = getAvgStars(reviewDataList);
            reviewStars[counter] = avgStars;

            counter++;

//            System.out.println("Counter: " + counter);
        }
        System.out.println("\t\tDone");

        PearsonsCorrelation correlation = new PearsonsCorrelation();

        double pearsonLength = correlation.correlation(reviewLength, penaltyScores);
        System.out.println("Pearson coefficient for review length: " + pearsonLength);

        double pearsonStars = correlation.correlation(reviewStars, penaltyScores);
        System.out.println("Pearson coefficient for average stars: " + pearsonStars);
    }

    private static Map<String, List<ReviewData>> buildMap(List<AllViolationData> violationList, List<ReviewData> reviewList) {

        Map<String, List<ReviewData>> map = new HashMap<>();

        for (AllViolationData violation : violationList) {
            String restaurentId = violation.getRestaurantID();
            List<ReviewData> list = new ArrayList<>();

            map.put(restaurentId, list);
        }

        for (ReviewData review : reviewList) {

            String restaurentId = review.getRestaurantId();
            List<ReviewData> partialList = map.get(restaurentId);

            partialList.add(review);

//            if (review.getRestaurantId().equals(restaurentId)) {
//                partialList.add(review);
//            }
        }

        return map;
    }

    public static double getAvgStars(List<ReviewData> list) {
        double avg = 0d;
        for (ReviewData data : list) {
            int star = data.getStars();
            avg += star;
        }

        return avg / list.size();
    }

    public static double getReviewLength(List<ReviewData> list) {
        double length = 0d;
        for (ReviewData data : list) {
            double reviewLength = data.getText().length();
            length += reviewLength;
        }
        return length;
    }

    public static List<ReviewData> getReviewForRestaurentID(String restaurentId,
                                                            List<ReviewData> reviewList) {

        List<ReviewData> partialList = new ArrayList<ReviewData>();
        for (ReviewData review : reviewList) {
            if (review.getRestaurantId().equals(restaurentId)) {
                partialList.add(review);
            }
        }

        return partialList;
    }
}
