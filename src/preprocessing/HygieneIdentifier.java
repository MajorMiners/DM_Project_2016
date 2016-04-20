package preprocessing;

import com.csvreader.CsvWriter;
import model.ReviewData;
import org.tartarus.snowball.ext.englishStemmer;
import parser.ReviewParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.List;

public class HygieneIdentifier {

    public static HashSet<String> hygieneDictionary = new HashSet<String>();

    public HashSet<String> getDict(){
    	return hygieneDictionary;
    }
    public static void readHygieneDictionary() {
        try {
            FileReader fr = new FileReader("data/hygiene_dictionary.txt");
            BufferedReader br = new BufferedReader(fr);
            englishStemmer stemmer = new englishStemmer();
            String line = br.readLine();
            while (line != null) {
                stemmer.setCurrent(line);
                if (stemmer.stem()) {
                    String stemLine = stemmer.getCurrent();
                    hygieneDictionary.add(stemLine);
                } else {
                    System.out.println("NOT stemmer stem :- " + line);
                    hygieneDictionary.add(line);
                }
                line = br.readLine();
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("unable to read hygiene-dictionary");
            System.exit(1);
        }
    }

    public static void writeToCsv(CsvWriter writer, ReviewData review, String newReview, int sentiment) {
        try {
            writer.write(newReview);
            writer.write(review.getBusinessId());
            writer.write(Integer.toString(review.getStars()));
            writer.write(review.getDate());
            writer.write(Integer.toString(review.getUsefulVotes()));
            writer.write(Integer.toString(sentiment));
            writer.endRecord();
        } catch (Exception e) {
            System.out.println("writeToCsv failed");
        }
    }

    public static void writeHeader(CsvWriter writer) {
        try {
            writer.write("text");
            writer.write("business_id");
            writer.write("stars");
            writer.write("date");
            writer.write("votes.useful");
            writer.write("sentiment");
            writer.endRecord();
        } catch (Exception e) {
            System.out.println("writeHeader failed");
        }
    }

    public static void classifyReviews(CsvWriter writer) {

        List<ReviewData> reviews = ReviewParser.readReviews();
        englishStemmer stemmer = new englishStemmer();
        for (String eachSet : hygieneDictionary) {
            System.out.println("+ " + eachSet);
        }
        for (ReviewData review : reviews) {
            String[] sentences = review.getText().split("\\. ");
            String newReview = "";
            boolean isReviewRelated = false;
            for (String sentence : sentences) {
                String[] words = sentence.split(" ");
                for (String eachWord : words) {
                    stemmer.setCurrent(eachWord);
                    if (stemmer.stem()) {
                        String stemWord = stemmer.getCurrent();
                        if (hygieneDictionary.contains(stemWord)) {
                            isReviewRelated = true;
                            newReview += sentence + ". "; // build a new review with sentences related to hygiene only
                            break;
                        }
                    } else {
                        System.out.println("NOT sentence stemmer stem :- " + eachWord);
                    }
                }
            }

            NLP.init();
            if (isReviewRelated) {
                int sentiment = NLP.findSentiment(newReview);
                writeToCsv(writer, review, newReview, sentiment);
            } else {
                int stars = review.getStars();
                if (stars >= 4) {
                    String text = review.getText();
                    int sentiment = NLP.findSentiment(text);
                    writeToCsv(writer, review, text, sentiment);
                }
            }
        }
    }
    
    public static void classifyText(CsvWriter writer) {

        List<ReviewData> reviews = ReviewParser.readReviews();
        englishStemmer stemmer = new englishStemmer();
        for (String eachSet : hygieneDictionary) {
            System.out.println("+ " + eachSet);
        }
        for (ReviewData review : reviews) {
            String[] sentences = review.getText().split("\\. ");
            String newReview = "";
            boolean isReviewRelated = false;
            
            for (String sentence : sentences) {
                String[] words = sentence.split(" ");
                
                for (String eachWord : words) {
                    stemmer.setCurrent(eachWord);
                    
                    if (stemmer.stem()) {
                        String stemWord = stemmer.getCurrent();
                        
                        if (hygieneDictionary.contains(stemWord)) {
                            isReviewRelated = true;
                            newReview += sentence + ". "; // build a new review with sentences related to hygiene only
                            break;
                        }
                    } else {
                        System.out.println("NOT sentence stemmer stem :- " + eachWord);
                    }
                }
            }

            NLP.init();
            if (isReviewRelated) {
                int sentiment = NLP.findSentiment(newReview);
                writeToCsv(writer, review, newReview, sentiment);
            } else {
                int stars = review.getStars();
                if (stars >= 4) {
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
        try {
            CsvWriter writer = new CsvWriter(new FileWriter("data/hygiene_related_reviews.csv", true), ',');
            writeHeader(writer); //write header to csv
            classifyReviews(writer); // read reviews, classify them and write to csv
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
