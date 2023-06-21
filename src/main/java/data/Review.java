package data;

import java.util.Objects;

public class Review {

    // who wrote it
    // move or tv
    // review text
    // like dislike boolean

    private String title;
    private String reviewText;
    private String reviewedBy;
    private boolean likeOrDislike;

    public Review(String title, String reviewText, String reviewedBy, boolean likeOrDislike) {
        this.title = title;
        this.reviewText = reviewText;
        this.reviewedBy = reviewedBy;
        this.likeOrDislike = likeOrDislike;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public boolean isLikeOrDislike() {
        return likeOrDislike;
    }

    public void setLikeOrDislike(boolean likeOrDislike) {
        this.likeOrDislike = likeOrDislike;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Review review = (Review) other;
        return likeOrDislike == review.likeOrDislike && Objects.equals(title, review.title) &&
                Objects.equals(reviewText, review.reviewText) && Objects.equals(reviewedBy, review.reviewedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, reviewText, reviewedBy, likeOrDislike);
    }
}
