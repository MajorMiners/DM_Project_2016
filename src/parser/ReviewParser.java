package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.AllViolationData;
import model.RestaurantToYelpIdData;
import model.ReviewData;

/**
 * Author: Joy and Isha
 * */
public class ReviewParser {

	private static String filePath = "data/yelp_academic_dataset_review.csv";
	
	public static void main(String args[])
	{
		
	}
	
	 public static List<ReviewData> readReadRestaurentToYelpData()
				throws IOException {
	    	
	    	List<ReviewData> list = new ArrayList<ReviewData>();
	    	BufferedReader br = new BufferedReader(new FileReader(filePath));
	        String line;
	        int linecount = 0;
	        while ((line = br.readLine()) != null) {

	            linecount++;

	            if (linecount > 1) {
	            	ReviewData lineData = getReviewData(line);
	                System.out.println(lineData);
	            }
	        }

	        br.close();
	        return list;
	    }
	    
	 private static ReviewData getReviewData(String line)
	 {
		 String[] tokens = line.split(",");
		 int tokenCount = tokens.length;
		 
		 RestaurantToYelpIdParser yelpToRestaurentParser = new RestaurantToYelpIdParser();
		 
		 String userId = tokens[0];
		 String reviewId = tokens[1];
		 String text = tokens[2];
		 int coolVotes = Integer.parseInt(tokens[3]);
		 int funnyVotes = Integer.parseInt(tokens[5]);
		 String businessID = tokens[4];
		 String restaurantId = yelpToRestaurentParser.getRestaurentIDFromYelpID(businessID);
		 int stars = Integer.parseInt(tokens[6]);
		 String date = tokens[7];
		 int usefulVotes = Integer.parseInt(tokens[8]);
		 
		 ReviewData data = new ReviewData(userId, reviewId, text, coolVotes, usefulVotes, funnyVotes,
				 stars, businessID, restaurantId, date);
		 
		 return data;
	 }
	
}
