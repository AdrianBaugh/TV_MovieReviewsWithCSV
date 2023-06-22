package service;

import dao.ReviewDao;
import data.Review;
import data.ReviewDataCsv;
import io.InputHandler;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReviewService {

    private ReviewDataCsv reviewDataCsv;
    private InputHandler inputHandler;
    private ReviewDao reviewDao;

    public ReviewService(ReviewDataCsv reviewDataCsv, InputHandler inputHandler, ReviewDao reviewDao) {
        this.reviewDataCsv = reviewDataCsv;
        this.inputHandler = inputHandler;
        this.reviewDao = reviewDao;
    }

    //  GET ALL REVIEWS AS LIST
    //  WAS PREVIOUSLY RETURNING A STRING WITH A RENDERED TABLE
    private List<Review> getAllReviews(){
        Map<String, List<Review>> reviewList = reviewDataCsv.getReviewList();
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

    //  A D D   T O   F A V O R I T E S
    private String addToFavorites(){
        List <FavData> newFavs = new ArrayList<>();
        while (true) {
            int bookId = inputHandler.getIntInput("\nEnter a book ID to add to favorites, or enter 0 to exit> ");

            if (bookId == 0) {
                break;
            }
            // if true say that book is  already in list
            List<FavData> favDataList = favoritesDao.getAllFavorites();
            List<Integer> idsList = new ArrayList<>();

            for (FavData fd : favDataList) {
                idsList.add(fd.getId());
            }
            if (idsList.contains(bookId) || newFavs.contains(bookId)){
                return "\nThat book is already in your Favorites List\n";
            }
            Book book = bookDao.getById(bookId);
            if (book == null) {
                System.out.println("\nInvalid book ID. Please enter a valid book ID.\n");
            } else {

                String comment = inputHandler.getString(("Add comment to " + book.getTitle()));
                FavData fd = new FavData(String.valueOf(bookId), comment);

                newFavs.add(fd);
                System.out.printf("\nBook '%s' by '%s' (ID: %d) has been added to favorites.%n",
                        book.getTitle(), book.getAuthor(), book.getId());
            }
        }

        if (newFavs.isEmpty()) {
            return "No books added to favorites.    ";
        }
        else {
            favoritesDao.addToFavorites(newFavs);
            return "Favorites List updated";
        }
    }

    //  R E M O V E   F R O M   F A V O R I T E S
    private String removeFromFavorites(){
        int bookId = inputHandler.getInteger("Enter a book ID to remove it from favorites, or enter 0 to exit> ");
        if (bookId == 0) {
            return "exit";
        }

        List<FavData> favDataList = favoritesDao.getAllFavorites();
        List<Integer> idsList = new ArrayList<>();

        for (FavData fd : favDataList) {
            idsList.add(fd.getId());
        }
        if (!idsList.contains(bookId)){
            return String.format("\nThere is no book by that ID: %d in your favorites list\n", bookId);
        }

        Book book = bookDao.getById(bookId);
        if (book == null){
            return String.format("\nThere is no book by that ID: %d\n", bookId);
        }
        favoritesDao.removeFromFavorites(bookId);
        return "removed " + book.getTitle() + " from your Favorites List";
    }
}
