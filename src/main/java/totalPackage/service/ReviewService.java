package totalPackage.service;

import totalPackage.Reviewer;
import totalPackage.dao.ReviewDao;
import totalPackage.data.Review;
import totalPackage.data.ReviewDataCsv;
import totalPackage.io.InputHandler;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReviewService {

    private ReviewDataCsv reviewDataCsv;// = new ReviewDataCsv();
    private InputHandler inputHandler;// = new InputHandler();
    private ReviewDao reviewDao;// = new ReviewDao(reviewDataCsv);
    private Reviewer reviewer;// = new Reviewer();

//    public ReviewService(Reviewer reviewer) {
//        this.reviewer = reviewer;
//    }
    @Inject
    public ReviewService(Reviewer reviewer, ReviewDataCsv reviewDataCsv, InputHandler inputHandler, ReviewDao reviewDao) {
        this.reviewer = reviewer;
        this.reviewDataCsv = reviewDataCsv;
        this.inputHandler = inputHandler;
        this.reviewDao = reviewDao;
    }

    //  GET ALL REVIEWS AS LIST
    //  WAS PREVIOUSLY RETURNING A STRING WITH A RENDERED TABLE
    public List<Review> getAllReviews(){
        Map<String, List<Review>> reviewList = reviewDataCsv.getReviewsMap();
        List<Review> results = new ArrayList<>();
        for (String title : reviewList.keySet()){

            results.addAll(reviewDao.getReviewsByTitle(title));
        }
        if (results.size() == 0) {
           // return "\nNo reviews in current List\n";
            return null;
        }
       // return renderFavListTable(results);
        return results;
    }


    //  ADD A NEW REVIEW
    public String addReview(){
        System.out.println("Enter the title to a movie or TV show to review: ");
        String title = inputHandler.getInput();
        if (title.contains(",")) {
            title = removeCommas(title);
        }

        System.out.println("Please enter your review now: ");
        String reviewText = inputHandler.getInput();
        if (reviewText.contains(",")) {
            reviewText = removeCommas(reviewText);
        }

        // add correct thumbs up function
        System.out.println("Overall thumbs-up or thumbs-down?");
        String thumbsUpDown = inputHandler.getInput();
        boolean likeOrDislike = true;
        if (thumbsUpDown.equals("thumbs-down") || thumbsUpDown.equals("down") || thumbsUpDown.equals("thumbs down")){
            likeOrDislike = false;
        }

        String currReviewerName = reviewer.getName();

        List<Review> currTitleReviews = reviewDao.getReviewsByTitle(title);

        Review newReview = new Review(title, reviewText, currReviewerName, likeOrDislike);
        currTitleReviews.add(newReview);

        Map<String, List<Review>> currReviewMap = reviewDao.getAllReviews();

        currReviewMap.put(title, currTitleReviews);

        reviewDao.addReviewToCsv(currReviewMap);
        return "Added your review for " + title;

    }

    private String removeCommas(String review) {
        String result = "";

        for (int i = 0; i < review.length(); i++) {
            Character currChar = review.charAt(i);
           if (currChar.equals(',')){
               result+= "";
           } else {
               result+=currChar;
           }
        }
        return result;
    }
}
