package Corelation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import model.AllViolationData;
import model.RestaurantToYelpIdData;
import model.ReviewData;
import model.ViolationEntry;
import parser.AllViolationParser;
import parser.RestaurantToYelpIdParser;
import parser.ReviewParser;

public class PearsonCorelation {

	public static void main(String[] args) throws IOException {
		
		AllViolationParser violationParser = new AllViolationParser();
		List<AllViolationData> violationList = new ArrayList<AllViolationData>();
		violationList = violationParser.readViolationData();
		
		ReviewParser reviewParser = new ReviewParser();
		List<ReviewData> reviewList =  reviewParser.readReviews();
		
		System.out.println("Reviews length: "+reviewList.size());
		
		double penaltyScores[] = new double[violationList.size()];
		double reviewLength[] = new double[violationList.size()];
		double reviewStars[] = new double[violationList.size()];
		int counter = 0;
		
		for(AllViolationData violation : violationList){
			String restaurentId = violation.getRestaurantID();
			List<ReviewData> reviewDataList = getReviewForRestaurentID(restaurentId, reviewList);
			ViolationEntry entry = violation.getViolationEntry();
			double penaltyScore = entry.calculatePenaltyScore(entry);
			penaltyScores[counter] = penaltyScore;
			double reviewlength = getReviewLength(reviewDataList);
			reviewLength[counter] = reviewlength;
			double avgStars = getAvgStars(reviewDataList);
			reviewStars[counter] = avgStars;
			counter++;
		}
		
		PearsonsCorrelation correlation = new PearsonsCorrelation();
	
		double pearsonLength = correlation.correlation(reviewLength, penaltyScores);
		System.out.println("Pearson coefficient for review length: "+pearsonLength);
	}
	
	public static double getAvgStars(List<ReviewData> list){
		double avg = 0d;
		for(ReviewData data : list){
			int star = data.getStars();
			avg += star;
		}
		
		return avg/list.size();
	}
	
	public static double getReviewLength(List<ReviewData> list){
		double length = 0d;
		for(ReviewData data : list){
			double reviewLength = data.getText().length();
			length+=reviewLength;
		}
		return length;
	}
	
	public static List<ReviewData> getReviewForRestaurentID(String restaurentId,
			List<ReviewData> reviewList){
		
		List<ReviewData> partialList = new ArrayList<ReviewData>();
		for(ReviewData review : reviewList){
			if(review.getRestaurantId().equals(restaurentId))
			{
				partialList.add(review);
			}
		}
		
		return partialList;
	}
	
}
