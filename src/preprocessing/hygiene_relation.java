package preprocessing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;

import com.csvreader.CsvWriter;

import model.ReviewData;
import parser.ReviewParser;

public class hygiene_relation {
	
	public static HashSet<String> hygieneDictionary = new HashSet<String>();
	
	public static void readHygieneDictionary(){
		try{
			FileReader fr = new FileReader("hygiene-dictionary.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line = br.readLine()) != null){
				hygieneDictionary.add(line)
			}
			br.close();
			fr.close();
		} catch (Exception e){
			System.out.println("unable to read hygiene-dictionary");
		}
	}

	public static void writeToCsv(CsvWriter writer, ReviewData review, String newReview){
		writer.write(newReview);
		writer.write(review.getBusinessId());
		writer.write(Integer.toString(review.getStars()));
		writer.write(review.getDate());
		writer.write(Integer.toString(review.getUsefulVotes()));
		writer.endRecord();
	}

	public static void writeToCsv(CsvWriter writer) {
		writer.write("text");
		writer.write("business_id");
		writer.write("stars");
		writer.write("date");
		writer.write("votes.useful");
		writer.endRecord();
	}

	public static void classifyReviews(CsvWriter writer) {
		ReviewParser reviewparser = new ReviewParser();
		List<ReviewData> reviews = reviewparser.readReviews();
		
		for(ReviewData review : reviews){
			String[] sentences = review.getText().split(". ");
			String newReview = "";
			boolean isReviewRelated = false;

			for(String sentence : sentences){
				for(String hygieneWord : hygieneDictionary) {
					if(sentence.contains(hygieneWord)) {
						isReviewRelated = true;
						newReview+=sentence+". "; // build a new review with sentences related to hygiene only
						break;
					}
				}
			}

			if(isReviewRelated){
				writeToCsv(writer, review, newReview);
			} else {
				int stars = review.getStars();
				if(stars>=4){
					writeToCsv(writer, review, review.getText());
				}
			}
		}
	}

	public static void main(String[] args) {
		preprocessData();
	}

	public static void preprocessData(){
		readHygieneDictionary();
		try{
			CsvWriter writer = new CsvWriter(new FileWriter("data/hygiene_related_reviews.csv", true), ',');
			writeHeader(writer); //write header to csv
			classifyReviews(writer); // read reviews, classify them and write to csv
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
