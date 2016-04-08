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
import org.tartarus.snowball.ext.englishStemmer;

import model.ReviewData;
import parser.ReviewParser;

public class HygieneIdentifier {
	
	public static HashSet<String> hygieneDictionary = new HashSet<String>();
	
	public static void readHygieneDictionary() {
		try{
			FileReader fr = new FileReader("hygiene-dictionary.txt");
			BufferedReader br = new BufferedReader(fr);
			englishStemmer stemmer = new englishStemmer();
			String line = null;
			while((line = br.readLine()) != null){
				stemmer.setCurrent(line);
				if(stemmer.stem()){
					hygieneDictionary.add(stemmer.getCurrent());
				}else{
					System.out.println("NOT stemmer stem :- "+line);
					hygieneDictionary.add(line);
				}
				
			}
			br.close();
			fr.close();
		} catch (Exception e){
			System.out.println("unable to read hygiene-dictionary");
		}
	}

	public static void writeToCsv(CsvWriter writer, ReviewData review, String newReview, int sentiment) {
		try{
			writer.write(newReview);
			writer.write(review.getBusinessId());
			writer.write(Integer.toString(review.getStars()));
			writer.write(review.getDate());
			writer.write(Integer.toString(review.getUsefulVotes()));
			writer.write(Integer.toString(sentiment));
			writer.endRecord();
		} catch (Exception e){
			System.out.println("writeToCsv failed");
		}
	}

	public static void writeHeader(CsvWriter writer) {
		try{
			writer.write("text");
			writer.write("business_id");
			writer.write("stars");
			writer.write("date");
			writer.write("votes.useful");
			writer.write("sentiment");
			writer.endRecord();
		} catch (Exception e){
			System.out.println("writeHeader failed");
		}
	}

	public static void classifyReviews(CsvWriter writer) {
		ReviewParser reviewparser = new ReviewParser();
		List<ReviewData> reviews = reviewparser.readReviews();
		englishStemmer stemmer = new englishStemmer();
		
		for(ReviewData review : reviews){
			String[] sentences = review.getText().split(". ");
			String newReview = "";
			boolean isReviewRelated = false;

			for(String sentence : sentences){
				String[] words = sentence.split(" ");
				String stemSentence = "";
				for(String eachWord : words){
					stemmer.setCurrent(eachWord);
					if(stemmer.stem()){
						if(hygieneDictionary.contains(stemmer.getCurrent())){
							isReviewRelated = true;
							newReview+=sentence+". "; // build a new review with sentences related to hygiene only
							break;
						}
					}else{
						System.out.println("NOT sentence stemmer stem :- "+eachWord);
					}
				}
			}

			NLP.init();
			if(isReviewRelated){
				int sentiment = NLP.findSentiment(newReview);
				writeToCsv(writer, review, newReview, sentiment);
			} else {
				int stars = review.getStars();
				if(stars>=4){
					String text = review.getText();
					int sentiment = NLP.findSentiment(text);
					writeToCsv(writer, review, text, sentiment);
				}
			}
		}
	}

	public static void main(String[] args) {
		preprocessData();
	}

	public static void preprocessData() {
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
