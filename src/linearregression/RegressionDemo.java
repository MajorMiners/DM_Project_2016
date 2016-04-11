package linearregression;/* Authored by Kushagra on 4/11/2016. */

import feature_parsers.FeatureParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Set;

public class RegressionDemo {

    public static void main(String[] args) throws IOException, ParseException {

        FeatureParser featureParser = new FeatureParser();

        Set<Integer> instances = featureParser.getInstances();
        for (int serialID : instances) {

            // Target Variables Y
            double Y = featureParser.getTargetVariable(serialID);

            // Predictors X
            double x1 = featureParser.getFeatureAverageLength(serialID);
            double x2 = featureParser.getFeatureAverageRating(serialID);
            double x3 = featureParser.getFeatureAverageReviewCount(serialID);
            double x4 = featureParser.getFeaturePenaltyScore(serialID);
            double x5 = featureParser.getFeatureReviewResponse(serialID);

            // TODO: More features to add here.

        }
    }
}
