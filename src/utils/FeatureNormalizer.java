package utils;/* Authored by Kushagra on 4/12/2016. */

// TODO: behaviour for categorical features
public class FeatureNormalizer {
   public static double normalizeBoolean(boolean booleanFeature) {
    	if(booleanFeature)
    		return 1;
    	else
    		return 0;
    }
   
   public static double normalizeGoodFeatures(boolean isGoodFeature) {
   	if(isGoodFeature)
   		return 0;
   	else
   		return 1;
   }


    public static double normalizeEnumForNoiseLevel(String noiseLevel) {
        switch(noiseLevel){
        	case "quiet": return 1;
        	case "average": return 2;
        	case "loud" : return 3;
        	case "very_loud": return 4;
        	default: return 0;
        }
    }

    public static double normalizeEnumForBusinessType(String businessType) {
        return 0;
    }

    public static double normalizeInteger(int stars) {
        return stars;
    }

    public static double normalizeEnumForPriceRange(int priceRange) {
    	switch(priceRange){
    	case 1: return 1;
    	case 2: return 2;
    	case 3: return 3;
    	case 4: return 4;
    	case 5: return 5;
    	default: return 0;
    	}
    }
}
