package dao;

import data.Review;
import data.ReviewDataCsv;

import java.util.Map;

public class ReviewDao {

    private ReviewDataCsv reviewDataCsv;

    public ReviewDao(ReviewDataCsv reviewDataCsv) {
        this.reviewDataCsv = reviewDataCsv;
    }

    // GET ALL REVIEWS
    public Map<String, Review> getAllReviews(){
        return reviewDataCsv.getReviewList();
    }

    // ADD NEW REVIEWS
    public void addReview(Map<String, Review> newReviews){
        reviewDataCsv.writeToReviewCsv(newReviews);
    }

    // REMOVE REVIEWS


}
