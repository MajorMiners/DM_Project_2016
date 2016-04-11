package linearregression;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.AllViolationData;
import model.ReviewData;
import model.ViolationEntry;
import parser.AllViolationParser;
import parser.ReviewParser;
import preprocessing.NLP;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.Utils;

public class LinearRegressionExample {

	public static void main(String[] args) throws IOException {
		
		List<String> hygenic = new ArrayList<String>(2);
		hygenic.add("yes");
		hygenic.add("no");
		
		Attribute reviewLength_attr = new Attribute("reviewLength");
		Attribute avgStars_attr = new Attribute("avgStars");
		Attribute oneStarViolation_attr = new Attribute("oneStarViolation");
		Attribute twoStarViolation_attr = new Attribute("twoStarViolation");
		Attribute threeStarViolation_attr = new Attribute("threeStarViolation");
		//Attribute sentiments_attr = new Attribute("sentiments");
		Attribute hygenic_attr = new Attribute("hygenic");
		
		ArrayList attrList = new ArrayList<Attribute>();

		attrList.add(reviewLength_attr);
		attrList.add(avgStars_attr);
		attrList.add(oneStarViolation_attr);
		attrList.add(twoStarViolation_attr);
		attrList.add(threeStarViolation_attr);
		//attrList.add(sentiments_attr);
		attrList.add(hygenic_attr);
		
		Instances data = new Instances("prediction", attrList, 0);
		int cIdx = data.numAttributes() - 1;
		data.setClassIndex(cIdx);
		
		AllViolationParser violationParser = new AllViolationParser();
        List<AllViolationData> violationList = new ArrayList<AllViolationData>();
        violationList = AllViolationParser.readViolationData();
        
        System.out.print("Calculating review lists...");
        List<ReviewData> reviewList = ReviewParser.readReviews();
        System.out.println("\t\tDone");
        
        Map<String, List<ReviewData>> restaurantIDReviewMap = buildMap(violationList, reviewList);
        
        int counter = 0;
        System.out.print("Calculating features... ");
        for (AllViolationData violation : violationList) {

        	Instance inst;
			inst = new DenseInstance(6);
            String restaurentId = violation.getRestaurantID();
            List<ReviewData> reviewDataList = restaurantIDReviewMap.get(restaurentId);     
            //int sentiment = getAvgSentimentCount(reviewDataList);
            ViolationEntry entry = violation.getViolationEntry();
            double reviewlength = getReviewLength(reviewDataList);
            double avgstars = getAvgStars(reviewDataList);
            inst.setValue(reviewLength_attr, reviewlength);
            inst.setValue(avgStars_attr, avgstars);
            inst.setValue(oneStarViolation_attr, entry.getMinorViolationCount());
            inst.setValue(twoStarViolation_attr, entry.getMajorViolationCount());
            inst.setValue(threeStarViolation_attr, entry.getSevereViolationCount());
            //inst.setValue(sentiments_attr, sentiment);
            if(entry.getSevereViolationCount()<= 1 && entry.getMajorViolationCount() <= 3)
            	inst.setValue(hygenic_attr, 0);
            else
            	inst.setValue(hygenic_attr, 1);
            
            data.add(inst);
            counter++;
        }
        
        System.out.println("\t\tDone");
        
        Instance newIsnt = new DenseInstance(6);
        newIsnt.setValue(reviewLength_attr, 50);
        newIsnt.setValue(avgStars_attr, 4);
        newIsnt.setValue(oneStarViolation_attr, 2);
        newIsnt.setValue(twoStarViolation_attr, 1);
        newIsnt.setValue(threeStarViolation_attr, 0);
        //newIsnt.setValue(sentiments_attr, 3);
        data.add(newIsnt);
        
        LinearRegression lrModel = new LinearRegression();
        
        try {
			  lrModel.buildClassifier(data);
			} catch (Exception e) {
			e.printStackTrace();
		}
        
        try {
        		Instance myPrediction = data.lastInstance();
				double prediction = lrModel.classifyInstance(myPrediction);
				System.out.println("My prediction: "+prediction);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("\t\tDone");
		
	}
	
	private static int getAvgSentimentCount(List<ReviewData> reviewDataList) {

		NLP nlp = new NLP();
		nlp.init();
		int total = 0;
		for(ReviewData review: reviewDataList){
			int sentimentVal = nlp.findSentiment(review.getText());
			total+=sentimentVal;
		}
		return total/reviewDataList.size();
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

}
