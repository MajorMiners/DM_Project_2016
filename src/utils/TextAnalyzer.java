package utils;/* Authored by Kushagra on 4/13/2016. */

import preprocessing.NLP;

public class TextAnalyzer {

    public static double baseScore = 2;

    public TextAnalyzer() {
        NLP.init();
    }


    public static double scoreReviewText(String reviewText) {

        if (isHygieneRelated(reviewText)) {
            return scoreHygienceRelatedText(reviewText);
        }
        else{
            return baseScore;
        }
    }

    private static double scoreHygienceRelatedText(String reviewText) {

        return NLP.findSentiment(reviewText);
    }

    private static boolean isHygieneRelated(String reviewText) {

        // TODO:

        return false;
    }
}
