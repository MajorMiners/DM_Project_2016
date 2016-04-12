package examples;/* Authored by Kushagra on 4/11/2016. */

import preprocessing.NLP;

public class SentimentAnalysis {
    public static void main(String[] args) {

        String review = "This is a great place to come over evening. Curry prepatation are particularly good";
        String review2 = "This is a very bad place to come over evening. Curry prepatation are particularly pathetic." +
                " Very dirty place. Would not recommend this to anyone";
        NLP.init();

//        for (int i=0; i<10000; i++) {
//            System.out.println(NLP.findSentiment(review));
//            System.out.println(i);
//        }

        System.out.println(NLP.findSentiment(review));
        System.out.println(NLP.findSentiment(review2));
        System.out.println(NLP.findSentiment("fine"));
    }
}
