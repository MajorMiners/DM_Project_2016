package wordleGenerator;/* Authored by Kushagra on 4/20/2016. */

import java.io.IOException;
import java.util.Arrays;

import static wordleGenerator.TopKWordFeatureList.getTopKWords;

public class TestString {
    public static void main(String[] args) throws IOException {
        String reviewText = " afsfa' star's da fda asdg adgadg a.     adfadf adf sad gdad as. asdg agsgas. ";

        // ... find top 1000 / update the frequency count of words found so far
        String[] sentence = TopKWordsGenerator.getWordsFromSentence(reviewText);

        System.out.println(Arrays.toString(sentence));

        String[] topWords = getTopKWords();

        System.out.println(Arrays.toString(topWords));


    }
}
