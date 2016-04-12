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

            // Predictors X -- Review.json
            double x1 = featureParser.getFeatureAverageLength(serialID);
            double x2 = featureParser.getFeatureAverageRating(serialID);
            double x3 = featureParser.getFeatureAverageReviewCount(serialID);
            double x4 = featureParser.getFeaturePenaltyScore(serialID);
            double x5 = featureParser.getFeatureReviewResponse(serialID);

            // Predictors X -- Business.json
            double x6 = featureParser.getFeatureIsAlcoholic(serialID);
            double x7 = featureParser.getFeatureisWaiterService(serialID);
            double x8 = featureParser.getFeatureisValetParking(serialID);
            double x9 = featureParser.getFeatureisRomantic(serialID);
            double x10 = featureParser.getFeatureisIntimate(serialID);
            double x11 = featureParser.getFeatureisTouristy(serialID);
            double x12 = featureParser.getFeatureisHipster(serialID);
            double x13 = featureParser.getFeatureisUpscale(serialID);
            double x14 = featureParser.getFeatureisDeliveryAvailable(serialID);
            double x15 = featureParser.getFeatureisGoodForDessert(serialID);
            double x16 = featureParser.getFeatureisLatenight(serialID);
            double x17 = featureParser.getEnumFeaturePriceRange(serialID);
            double x18 = featureParser.getFeatureIsBusinessStars(serialID);
            double x19 = featureParser.getEnumFeaturebusinessType(serialID);
            double x20 = featureParser.getEnumFeatureNoiseLevel(serialID);

            // TODO: More features to add here.

        }

        //

    }
}
