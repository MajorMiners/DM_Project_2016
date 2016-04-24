package utils;/* Authored by Kushagra on 4/13/2016. */

import java.util.HashSet;

import org.tartarus.snowball.ext.englishStemmer;

import preprocessing.NLP;

@SuppressWarnings("ConstantConditions")
public class TextAnalyzer {

    public static double baseScore = 2;

    public TextAnalyzer() {
        NLP.init();
    }

    public static double scoreReviewText(String reviewText, HashSet<String> dict) {
    	String newReview = hygieneComment(reviewText,dict);
    	if(newReview.equals("")) {
            return baseScore;
        } else {
        	return scoreHygienceRelatedText(newReview);
        }
        
    }

    private static double scoreHygienceRelatedText(String reviewText) {
        return NLP.findSentiment(reviewText);
    }

    public static String hygieneComment(String reviewText, HashSet<String> dict) {
    	
    	englishStemmer stemmer = new englishStemmer();
    	String[] sentences = reviewText.split("\\. ");
        String newReview = "";
        
        for (String sentence : sentences) {
            String[] words = sentence.split(" ");
            for (String eachWord : words) {
                stemmer.setCurrent(eachWord);
                if (stemmer.stem()) {
                    String stemWord = stemmer.getCurrent();
                    if (dict.contains(stemWord)) {
                        newReview += sentence + ". "; // build a new review with sentences related to hygiene only
                        break;
                    }
                }
            }
        }
    	
    	return newReview;     // turn off NLP
    }
    
    public static boolean isHygieneRelated(String reviewText, HashSet<String> dict){
    	for(String dictWord: dict){
    		if(reviewText.contains(dictWord)){
    			
    			return true;
    		}
    	}
    	return false;
    }
}
