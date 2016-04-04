package preprocessing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.csvreader.CsvWriter;

import model.ReviewData;
import parser.ReviewParser;

public class hygiene_relation {
	
	public static Set<String> hs = new HashSet<String>();
	
	public static void main(String[] args) throws IOException {
		
		
		//add elements to set
		hs.add("mess");
		hs.add("dirty");
		hs.add("table");
		hs.add("rat");
		hs.add("horrible");
		hs.add("awful");
		hs.add("raw");
		hs.add("bathroom");
		hs.add("hygiene");
		hs.add("ill");
		hs.add("bad");
		hs.add("cheap");
		hs.add("dark");
		hs.add("floor");
		hs.add("pleasant");
		hs.add("hair");
		hs.add("wash");
		hs.add("paper");
		hs.add("soap");
		hs.add("cold");
		hs.add("gross");
		hs.add("filthy");
		hs.add("crappy");
		hs.add("sanitation");
		hs.add("regimen");
		hs.add("healthy");
		hs.add("wholesome");
		hs.add("foul");
		hs.add("heaven");
		hs.add("big");
		hs.add("large");
		hs.add("glass");
		hs.add("fork");
		hs.add("plate");
		hs.add("spoon");
		hs.add("knife");
		hs.add("chair");
		hs.add("disgusting");
		hs.add("fresh");
		hs.add("odor");
		hs.add("unsanitary");
		hs.add("gloves");
		hs.add("eww");
		hs.add("greasy");
		hs.add("hot");
		hs.add("quality");
		hs.add("ingredient");
		hs.add("low");
		hs.add("stale");
		hs.add("bathroom");
		hs.add("wall");
		hs.add("smoke");
		hs.add("stain");
		hs.add("undercook");
		
		try{
			String outputFile = "data/hygiene_related_reviews.csv";
			String outputFile1 = "data/hygiene_reviews.csv";
			// before we open the file check to see if it already exists
			boolean alreadyExists = new File(outputFile).exists();
			
			CsvWriter writer = new CsvWriter(new FileWriter("data/hygiene_related_reviews.csv", true), ',');
			CsvWriter writer1 = new CsvWriter(new FileWriter("data/hygiene_reviews.csv", true), ',');
			
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				writer.write("user_id");
				writer.write("review_id");
				writer.write("text");
				writer.write("votes.cool");
				writer.write("business_id");
				writer.write("votes.funny");
				writer.write("stars");
				writer.write("date");
				writer.write("votes.useful");
				writer.endRecord();
				
				writer1.write("user_id");
				writer1.write("review_id");
				writer1.write("text");
				writer1.write("votes.cool");
				writer1.write("business_id");
				writer1.write("votes.funny");
				writer1.write("stars");
				writer1.write("date");
				writer1.write("votes.useful");
				writer1.endRecord();
			}
			// else assume that the file already has the correct header line
			ReviewParser reviewparser = new ReviewParser();
			List<ReviewData> reviews = reviewparser.readReviews();
			
			for(ReviewData review : reviews){
				String text = review.getText();
				Iterator itr = hs.iterator();
				
				while(itr.hasNext()){
					
					Object entry = itr.next();
					if(text.contains(entry.toString())){
						writer.write(review.getUserId());
						writer.write(review.getReviewId());
						writer.write(text);
						writer.write(Integer.toString(review.getCoolVotes()));
						writer.write(review.getBusinessId());
						writer.write(Integer.toString(review.getFunnyVotes()));
						writer.write(Integer.toString(review.getStars()));
						writer.write(review.getDate());
						writer.write(Integer.toString(review.getUsefulVotes()));
						writer.endRecord();
						break;
					}
					else{
						writer1.write(review.getUserId());
						writer1.write(review.getReviewId());
						writer1.write(text);
						writer1.write(Integer.toString(review.getCoolVotes()));
						writer1.write(review.getBusinessId());
						writer1.write(Integer.toString(review.getFunnyVotes()));
						writer1.write(Integer.toString(review.getStars()));
						writer1.write(review.getDate());
						writer1.write(Integer.toString(review.getUsefulVotes()));
						writer1.endRecord();
					}
						
				}
			}
			writer.close();
			writer1.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
