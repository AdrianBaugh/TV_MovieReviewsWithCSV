package dao;

import data.Review;
import data.ReviewDataCsv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewDao {

    private ReviewDataCsv reviewDataCsv;

    public ReviewDao(ReviewDataCsv reviewDataCsv) {
        this.reviewDataCsv = reviewDataCsv;
    }


    // GET ALL REVIEWS
    public Map<String, List<Review>> getAllReviews(){
        return reviewDataCsv.getReviewList();
    }

    // GET REVIEWS BY TITLE
    public List<Review> getReviewsByTitle(String title) {
        Map<String, List<Review>> reviewMap = new HashMap<>(getAllReviews());
        return reviewMap.get(title);

    }
    // ADD NEW REVIEWS
    public void addReviewToCsv(Map<String, Review> newReviews){
        reviewDataCsv.writeToReviewCsv(newReviews);
    }

    // REMOVE REVIEWS


}
