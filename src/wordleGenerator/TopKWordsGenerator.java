package wordleGenerator;/* Authored by Kushagra on 4/14/2016. */

import feature_parsers.FeatureInstance;
import model.Review;
import model.ReviewSet;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import static feature_parsers.ReviewParser.buildReviewSetMap;

public class TopKWordsGenerator {

    public static void main(String[] args) throws IOException, ParseException {


        Map<Integer, FeatureInstance> instanceMap = FeatureInstance.getMap_Instances();
        Map<Integer, ReviewSet> reviewSetMapper = buildReviewSetMap(instanceMap);
        
        int k = 1100;
        Map<String, Integer> TermFreq = new HashMap<String, Integer>();
        PriorityQueue<WordFreq> topKHeap = new PriorityQueue<WordFreq>(k);
        
        for (int serialID : instanceMap.keySet()) {

            // get the list of reviews for the 'serialID'
            ReviewSet reviewSet = reviewSetMapper.get(serialID);

            // get the list of reviews from
            List<Review> reviews =  reviewSet.getReviewSet();

            for (Review review : reviews) {

                // get a particular review from the list of reviews
                String reviewText = review.getText();

                // ... find top 1000 / update the frequency count of words found so far
                String[] sentence = reviewText.toLowerCase().trim().split(".");
                for(String s : sentence){
                	String[] words = s.toLowerCase().trim().split(" ");
                	
                	for (final String word : words) {
                        int freq = 1;
                        if (TermFreq.containsKey(word)) {
                            freq = TermFreq.get(word) + 1;
                        }

                        // update the frequency map
                        TermFreq.put(word, freq);
                    }
                }
            }
            
            // Build the topK heap
            for (final java.util.Map.Entry<String, Integer> entry : TermFreq.entrySet()) {
                
            	if (topKHeap.size() < k) {
            		WordFreq wf = new WordFreq(entry.getKey(), entry.getValue());
                    topKHeap.add(wf);
                } else if (entry.getValue() > topKHeap.peek().freq) {
                    topKHeap.remove();
                    topKHeap.add(new WordFreq(entry.getKey(), entry.getValue()));
                }
            }
            
            // extract the top K
            final String[] topK = new String[k];
            int i = 0;
            while (topKHeap.size() > 0) {
                topK[i++] = topKHeap.remove().word;
            }
            
            int cnt=1;
            for(String topWord : topK){
            	System.out.println(cnt + ") \t" + topK[cnt]);
            	cnt++;
            }

            // return topK;


        }



    }

    public static class WordFreq implements Comparable<WordFreq> {
        String word;
        int freq;

        public WordFreq(final String w, final int c) {
            word = w;
            freq = c;
        }

        @Override
        public int compareTo(final WordFreq other) {
            return Integer.compare(this.freq, other.freq);
        }
    }


}
