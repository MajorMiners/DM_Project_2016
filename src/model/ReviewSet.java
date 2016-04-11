package model;/* Authored by Kushagra on 4/10/2016. */

import java.util.ArrayList;
import java.util.List;

public class ReviewSet {

    List<Review> reviewSet;
    double averageLength;
    double averageRating;
    int reviewCount;


    public ReviewSet() {
        this.reviewSet = new ArrayList<>();
    }

    public ReviewSet(List<Review> reviewSet) {
        this.reviewSet = reviewSet;
        this.averageLength = getAverageLengthFromSet(reviewSet);
        this.averageRating = getAverageRating(reviewSet);
        this.reviewCount = getReviewCount(reviewSet);
    }

    private int getReviewCount(List<Review> reviewSet) {

        return reviewSet.size();
    }

    private double getAverageRating(List<Review> reviewSet) {
        double average;
        double ratingSum = 0;

        for (Review review : reviewSet) {
            ratingSum += review.userRating;
        }

        average = ratingSum / reviewSet.size();
        return average;
    }

    private double getAverageLengthFromSet(List<Review> reviewSet) {
        String space = " ";
        double average;
        double totalLength = 0;

        for (Review review : reviewSet) {

            int currentLength = review.text.split(space).length;
            totalLength += currentLength;
        }

        average = totalLength / reviewSet.size();
        return average;
    }
}
