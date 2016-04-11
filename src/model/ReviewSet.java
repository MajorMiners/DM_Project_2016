package model;/* Authored by Kushagra on 4/10/2016. */

import java.util.ArrayList;
import java.util.List;

public class ReviewSet {

    private List<Review> reviewSet;
    private double averageLength;
    private double averageRating;
    private int reviewCount;
    // TODO: add review response


    public ReviewSet() {
        this.reviewSet = new ArrayList<>();
    }

    public static void FillEntriesInReviewSet(ReviewSet reviewSet) {
        reviewSet.setAverageLength(getAverageLengthFromSet(reviewSet.getReviewSet()));
        reviewSet.setAverageRating(getAverageRating(reviewSet.getReviewSet()));
        reviewSet.setReviewCount(getReviewCount(reviewSet.getReviewSet()));
    }

    private static int getReviewCount(List<Review> reviewSet) {

        return reviewSet.size();
    }

    private static double getAverageRating(List<Review> reviewSet) {
        double average;
        double ratingSum = 0;

        for (Review review : reviewSet) {
            ratingSum += review.getUserRating();
        }

        average = ratingSum / reviewSet.size();
        return average;
    }

    private static double getAverageLengthFromSet(List<Review> reviewSet) {
        String space = " ";
        double average;
        double totalLength = 0;

        for (Review review : reviewSet) {

            int currentLength = review.getText().split(space).length;
            totalLength += currentLength;
        }

        average = totalLength / reviewSet.size();
        return average;
    }

    public List<Review> getReviewSet() {
        return reviewSet;
    }

    public void setReviewSet(List<Review> reviewSet) {
        this.reviewSet = reviewSet;
    }

    public double getAverageLength() {
        return averageLength;
    }

    public void setAverageLength(double averageLength) {
        this.averageLength = averageLength;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
}
