package feature_parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ReviewData;
import parser.ReviewParser;

public class TextInstances {
	Map<String,Integer> table = new HashMap<String,Integer>();
	
	List<ReviewData> reviews = ReviewParser.readReviews();
	
	public void populateTableMap(){
		
	}
	
	
}
