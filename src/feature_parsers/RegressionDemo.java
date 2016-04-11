package feature_parsers;/* Authored by Kushagra on 4/11/2016. */

import model.AllViolationData;
import model.Review;
import model.ReviewSet;
import org.json.simple.parser.ParseException;
import parser.AllViolationParser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RegressionDemo {

    public static void main(String[] args) throws IOException, ParseException {

        // Preprocessors
        List<AllViolationData> entries = AllViolationParser.readViolationData();
        Map<Integer, Instance> instanceMap = Instance.getMap_Instances();

        // Predictors
        Map<Integer, ReviewSet> reviewSetMapper = ReviewParser.buildReviewSetMap(instanceMap);
        Map<Integer, Double> map_serialID_pastViolation = PastPenalty.buildPastViolationsMap(entries);

        // Target Variable Y
        Map<Integer, Double> mapTagetVariable = TargetVariable.getMap_TargetVariable(1, 1, 1);

        for (int serialID : mapTagetVariable.keySet()) {

            // Target Variable [Penalty Score]
            double Y = mapTagetVariable.get(serialID);

            // Features
            double x1 = map_serialID_pastViolation.get(serialID);
            double x2 = reviewSetMapper.get(serialID).getAverageLength();
            double x3 = reviewSetMapper.get(serialID).getAverageRating();
            double x4 = reviewSetMapper.get(serialID).getReviewCount();
            double x5 = reviewSetMapper.get(serialID).getReviewResponse();

            // TODO: score from text analysis of reviews
            double x6 = getNumericScoreFromTextAnalysis(reviewSetMapper.get(serialID).getReviewSet());

            // TODO: Regression Model
        }
    }

    // TODO: returns a score for the regression model, from the list of reviews, for the given inspection set.
    private static double getNumericScoreFromTextAnalysis(List<Review> reviewSet) {

        double totalScore = 0;

        for (Review review : reviewSet) {

            String reviewText = review.getText();                       // review text added by users
            int responseCount = review.getReviewResponseCount();        // votes count = useful / funny / cool
            int userProfile = 0;                                        // TODO: KV [Numeric Measure of user's words]

            totalScore += assignScore(reviewText, userProfile, responseCount);
        }

        // return the average of scores
        return totalScore / reviewSet.size();
    }

    // TODO: text analysis method signature
    private static double assignScore(String reviewText, int userProfile, int responseCount) {
        return 0;
    }
}
