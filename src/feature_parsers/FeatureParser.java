package feature_parsers;/* Authored by Kushagra on 4/11/2016. */

import feature_parsers.Instance;
import feature_parsers.PastPenalty;
import feature_parsers.ReviewParser;
import feature_parsers.TargetVariable;
import model.AllViolationData;
import model.ReviewSet;
import org.json.simple.parser.ParseException;
import parser.AllViolationParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("UnnecessaryLocalVariable")
public class FeatureParser {

    // Preprocessors
    List<AllViolationData> entries;
    Map<Integer, Instance> instanceMap;

    // Predictors X
    Map<Integer, ReviewSet> reviewSetMapper;
    Map<Integer, Double> map_serialID_pastViolation;

    // Target Variable Y
    Map<Integer, Double> mapTargetVariable;


    // Class private locators
    private Map<Integer, Double> averageLength;
    private Map<Integer, Double> averageRating;
    private Map<Integer, Double> averageReviewCount;
    private Map<Integer, Double> averageReviewResponse;
    private Map<Integer, Double> averagePenaltyScore;

    private Map<Integer, Double> targetVariable;

    public FeatureParser() throws IOException, ParseException {

        buildParser();

        averageLength = populateFeatureMap1();
        averageRating = populateFeatureMap2();
        averageReviewCount = populateFeatureMap3();
        averageReviewResponse = populateFeatureMap4();
        averagePenaltyScore = populateFeatureMap5();

        targetVariable = populateTargetVariable();
    }

    private Map<Integer, Double> populateTargetVariable() {

        Map<Integer, Double> map = new HashMap<>();

        for (int serialID : mapTargetVariable.keySet()) {

            int key = serialID;
            Double value = mapTargetVariable.get(serialID);

            map.put(key, value);
        }

        return map;
    }

    public Set<Integer> getInstances() {
        return this.averageLength.keySet();
    }

    public double getTargetVariable(int serialID) {
        return targetVariable.get(serialID);
    }

    public double getFeatureAverageLength(int serialID) {
        return averageLength.get(serialID);
    }

    public double getFeatureAverageRating(int serialID) {
        return averageRating.get(serialID);
    }

    public double getFeatureAverageReviewCount(int serialID) {
        return averageReviewCount.get(serialID);
    }

    public double getFeatureReviewResponse(int serialID) {
        return averageReviewResponse.get(serialID);
    }

    public double getFeaturePenaltyScore(int serialID) {
        return averagePenaltyScore.get(serialID);
    }


    private Map<Integer, Double> populateFeatureMap1() {

        Map<Integer, Double> map = new HashMap<>();

        for (int sampleID : reviewSetMapper.keySet()) {

            int key = sampleID;
            double value = reviewSetMapper.get(sampleID).getAverageLength();

            map.put(key, value);
        }

        return map;
    }

    private Map<Integer, Double> populateFeatureMap2() {

        Map<Integer, Double> map = new HashMap<>();

        for (int sampleID : reviewSetMapper.keySet()) {

            int key = sampleID;
            double value = reviewSetMapper.get(sampleID).getAverageRating();

            map.put(key, value);
        }

        return map;
    }

    private Map<Integer, Double> populateFeatureMap3() {

        Map<Integer, Double> map = new HashMap<>();

        for (int sampleID : reviewSetMapper.keySet()) {

            int key = sampleID;
            double value = reviewSetMapper.get(sampleID).getReviewCount();

            map.put(key, value);
        }

        return map;
    }

    private Map<Integer, Double> populateFeatureMap4() {

        Map<Integer, Double> map = new HashMap<>();

        for (int sampleID : reviewSetMapper.keySet()) {

            int key = sampleID;
            double value = reviewSetMapper.get(sampleID).getReviewResponse();

            map.put(key, value);
        }

        return map;
    }

    private Map<Integer, Double> populateFeatureMap5() {

        Map<Integer, Double> map = new HashMap<>();

        for (int sampleID : map_serialID_pastViolation.keySet()) {

            int key = sampleID;
            double value = map_serialID_pastViolation.get(sampleID);

            map.put(key, value);
        }

        return map;
    }


    private void buildParser() throws IOException, ParseException {

        // Preprocessors
        this.entries = AllViolationParser.readViolationData();
        this.instanceMap = Instance.getMap_Instances();

        // Predictors X
        this.reviewSetMapper = ReviewParser.buildReviewSetMap(instanceMap);
        this.map_serialID_pastViolation = PastPenalty.buildPastViolationsMap(entries);

        // Target Variable Y
        this.mapTargetVariable = TargetVariable.getMap_TargetVariable(1, 1, 1);
    }

    public void setAverageLength(Map<Integer, Double> averageLength) {
        this.averageLength = averageLength;
    }

    public void setAverageRating(Map<Integer, Double> averageRating) {
        this.averageRating = averageRating;
    }

    public void setAverageReviewCount(Map<Integer, Double> averageReviewCount) {
        this.averageReviewCount = averageReviewCount;
    }

    public void setAverageReviewResponse(Map<Integer, Double> averageReviewResponse) {
        this.averageReviewResponse = averageReviewResponse;
    }

    public void setAveragePenaltyScore(Map<Integer, Double> averagePenaltyScore) {
        this.averagePenaltyScore = averagePenaltyScore;
    }
}
