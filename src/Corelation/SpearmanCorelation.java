package corelation;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

import feature_parsers.FeatureParser;
import weka.attributeSelection.CorrelationAttributeEval;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class SpearmanCorelation {
	
	public static void main(String[] args) throws Exception {
		FeatureParser featureParser = new FeatureParser();
		 
		 Set<Integer> trainInstances = featureParser.getInstances();
		 double[]  f1 = new double[trainInstances.size()];
		 double[]  f2 = new double[trainInstances.size()];
		 double[]  f3 = new double[trainInstances.size()];
		 double[]  f4 = new double[trainInstances.size()];
		 double[]  f5 = new double[trainInstances.size()];
		 double[]  f6 = new double[trainInstances.size()];
		 double[]  f7 = new double[trainInstances.size()];
		 double[]  f8 = new double[trainInstances.size()];
		 double[]  f9 = new double[trainInstances.size()];
		 double[]  f10 = new double[trainInstances.size()];
		 double[]  f11 = new double[trainInstances.size()];
		 double[]  f12 = new double[trainInstances.size()];
		 double[]  f13 = new double[trainInstances.size()];
		 double[]  f14 = new double[trainInstances.size()];
		 double[]  f15 = new double[trainInstances.size()];
		 double[]  f16 = new double[trainInstances.size()];
		 double[]  f17 = new double[trainInstances.size()];
		 double[]  f18 = new double[trainInstances.size()];
		 double[]  f19 = new double[trainInstances.size()];
		 double[]  f20 = new double[trainInstances.size()];
		 double[] f21 = new double[trainInstances.size()];
		 
		 double[]  Y= new double[trainInstances.size()];
		 int cnt=0;
		 for (int serialID : trainInstances) {
        	
            // Target Variables Y
            Y[cnt] = featureParser.getTargetVariable(serialID);
            // Predictors X -- Review.json
            f1[cnt] = featureParser.getFeatureAverageLength(serialID);
            f2[cnt] = featureParser.getFeatureAverageRating(serialID);
            f3[cnt] = featureParser.getFeatureAverageReviewCount(serialID);
            f4[cnt] = featureParser.getFeaturePenaltyScore(serialID);
            f5[cnt] = featureParser.getFeatureReviewResponse(serialID);

            // Predictors X -- Business.json
            f6[cnt] = featureParser.getFeatureIsAlcoholic(serialID);
            f7[cnt] = featureParser.getFeatureisWaiterService(serialID);
            f8[cnt] = featureParser.getFeatureisValetParking(serialID);
            f9[cnt] = featureParser.getFeatureisRomantic(serialID);
            f10[cnt] = featureParser.getFeatureisIntimate(serialID);
            f11[cnt] = featureParser.getFeatureisTouristy(serialID);
            f12[cnt] = featureParser.getFeatureisHipster(serialID);
            f13[cnt] = featureParser.getFeatureisUpscale(serialID);
            f14[cnt] = featureParser.getFeatureisDeliveryAvailable(serialID);
            f15[cnt] = featureParser.getFeatureisGoodForDessert(serialID);
            f16[cnt] = featureParser.getFeatureisLatenight(serialID);
            f17[cnt] = featureParser.getEnumFeaturePriceRange(serialID);
            f18[cnt] = featureParser.getFeatureIsBusinessStars(serialID);
            //f19[cnt] = featureParser.getEnumFeaturebusinessType(serialID);
            f20[cnt] = featureParser.getEnumFeatureNoiseLevel(serialID);
            f21[cnt] = featureParser.getEnumFeatureCusineType(serialID);
           // double x21 = featureParser.getTextAnalysisScore(serialID);      // fake score of 2, for now  
           cnt++;
        }
		 System.out.println("Count: "+ cnt);
		 SpearmansCorrelation eval = new SpearmansCorrelation();
		 System.out.println("AverageLength: "+ eval.correlation(f1, Y));
		 System.out.println("AverageRating: "+ eval.correlation(f2, Y));
		 System.out.println("AverageReviewCount: "+ eval.correlation(f3, Y));
		 System.out.println("PenaltyScore: "+ eval.correlation(f4, Y));
		 System.out.println("ReviewResponse: "+ eval.correlation(f5, Y));
		 System.out.println("IsAlcoholic: "+ eval.correlation(f6, Y));
		 System.out.println("hasWaiterService: "+ eval.correlation(f7, Y));
		 System.out.println("hasValetParking: "+ eval.correlation(f8, Y));
		 System.out.println("isRomantic: "+ eval.correlation(f9, Y));
		 System.out.println("isIntimate: "+ eval.correlation(f10, Y));
		 System.out.println("isTouristy: "+ eval.correlation(f11, Y));
		 System.out.println("isHipster: "+ eval.correlation(f12, Y));
		 System.out.println("isUpscale: "+ eval.correlation(f13, Y));
		 System.out.println("isDeliveryAvailable: "+ eval.correlation(f14, Y));
		 System.out.println("isGoodForDessert: "+ eval.correlation(f15, Y));
		 System.out.println("isLatenight: "+ eval.correlation(f16, Y));
		 System.out.println("PriceRange: "+ eval.correlation(f17, Y));
		 System.out.println("IsBusinessStars: "+ eval.correlation(f18, Y));
		 System.out.println("NoiseLevel: "+ eval.correlation(f20, Y));
		 System.out.println("CuisineType: "+ eval.correlation(f21, Y));
		 
	}
}
