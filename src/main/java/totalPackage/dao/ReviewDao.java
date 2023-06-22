package totalPackage.dao;

import totalPackage.data.Review;
import totalPackage.data.ReviewDataCsv;

import java.util.ArrayList;
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
    // if no reviews for that title return an empty list
    public List<Review> getReviewsByTitle(String title) {

        Map<String, List<Review>> reviewMap = new HashMap<>(getAllReviews());
        if (!reviewMap.containsKey(title)) {
            return new ArrayList<>();
        }
        return reviewMap.get(title);

    }
    // ADD NEW REVIEWS
    public void addReviewToCsv(Map<String, Review> newReviews){
        reviewDataCsv.writeToReviewCsv(newReviews);
    }

    // REMOVE REVIEWS


}
