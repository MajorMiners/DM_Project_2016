package wordleGenerator;/* Authored by Kushagra on 4/14/2016. */

import feature_parsers.FeatureInstance;
import model.Review;
import model.ReviewSet;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static feature_parsers.ReviewParser.buildReviewSetMap;

public class TopKWordsGenerator {

    public static void main(String[] args) throws IOException, ParseException {


        Map<Integer, FeatureInstance> instanceMap = FeatureInstance.getMap_Instances();
        Map<Integer, ReviewSet> reviewSetMapper = buildReviewSetMap(instanceMap);

        for (int serialID : instanceMap.keySet()) {

            // get the list of reviews for the 'serialID'
            ReviewSet reviewSet = reviewSetMapper.get(serialID);

            // get the list of reviews from
            List<Review> reviews =  reviewSet.getReviewSet();

            for (Review review : reviews) {

                // get a particular review from the list of reviews
                String reviewText = review.getText();

                // ... find top 1000 / update the frequency count of words found so far

            }


        }



    }




}
