package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.AllViolationData;
import model.RestaurantToYelpIdData;
import model.ReviewData;

import com.csvreader.CsvReader;

/**
 * Author: Joy and Isha
 * */
public class ReviewParser {

	private static String filePath = "data/yelp_academic_dataset_review.csv";
	
	public static void main(String args[])
	{
		
	}
	
	 public static List<ReviewData> readReviews()
				throws IOException {
		 List<ReviewData> list = new ArrayList<ReviewData>();
		 try {
				 CsvReader reviews = new CsvReader(filePath);
				 reviews.readHeaders();
				 RestaurantToYelpIdParser yelpToRestaurentParser = new RestaurantToYelpIdParser();
				 
				 while (reviews.readRecord()){
					 String userId = reviews.get("user_id");
					 String reviewId = reviews.get("review_id");
					 String text = reviews.get("text");
					 int coolVotes = Integer.parseInt(reviews.get("votes.cool"));
					 int funnyVotes = Integer.parseInt(reviews.get("votes.funny"));
					 String businessID = reviews.get("business_id");
					 String restaurantId = yelpToRestaurentParser.getRestaurentIDFromYelpID(businessID);
					 int stars = Integer.parseInt(reviews.get("stars"));
					 String date = reviews.get("date");
					 int usefulVotes = Integer.parseInt(reviews.get("votes.useful"));
					 
					 ReviewData data = new ReviewData(userId, reviewId, text, coolVotes, usefulVotes, funnyVotes,
							 stars, businessID, restaurantId, date);
					 
					 list.add(data);
					}
				 reviews.close();
		 }catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 return list;
	 }
	
}
