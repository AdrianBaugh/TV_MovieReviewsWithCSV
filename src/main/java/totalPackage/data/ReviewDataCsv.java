package totalPackage.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewDataCsv {

    private static final String REVIEW_DATA_RESOURCE = "reviewData.csv";

    private static final String REVIEW_DATA_FILENAME = System.getProperty("user.home") + "/.TV_MovieReviewsFromCSV/" + REVIEW_DATA_RESOURCE;

    private Map<String, List<Review>> reviewList;

    public ReviewDataCsv() {

        if (!Files.exists(Path.of(REVIEW_DATA_FILENAME))) {
            try {

                InputStream input = this.getClass().getClassLoader().getResourceAsStream(REVIEW_DATA_RESOURCE);

                Path destinationFile = Path.of(REVIEW_DATA_FILENAME);
                Path destinationDir = destinationFile.getParent();
                if (!Files.exists(destinationDir)) {
                    Files.createDirectory(destinationDir);
                }
                Files.copy(input, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Unable to write copy source data file", e);
            }
        }
        readReviewsFromCSV();
    }

    public void readReviewsFromCSV() {

        if (this.reviewList == null) {
            this.reviewList = new HashMap<>();
        }
        try {
            FileInputStream input = new FileInputStream(REVIEW_DATA_FILENAME);
            Reader csvReader = new InputStreamReader(input);

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(csvReader);
            for (CSVRecord record : records) {
                //row headers in csv needs to be skipped
                if (record.getRecordNumber() == 1) {
                    continue;
                }
                // if csv is empty break the for loop
                if (record.get(1).isEmpty()) {
                    break;
                }
                // current record review parameters
                String currRecordTitle = record.get(0);
                String currReviewText = record.get(1);
                String currReviewer = record.get(2);
                boolean currLikeDislike = false;
                if (record.get(3).equals("true")) {
                    currLikeDislike = true;
                }
                //construct a new review
                Review currReview = new Review(currRecordTitle, currReviewText, currReviewer, currLikeDislike);
                //add review to map by its tv or movie title
                if (reviewList.containsKey(currRecordTitle)) {
                    List<Review> currTitleReviews = reviewList.get(currRecordTitle);
                    currTitleReviews.add(currReview);
                    this.reviewList.put(currRecordTitle, currTitleReviews);
                } else {
                    List<Review> newReviewlist = new ArrayList<>();
                    newReviewlist.add(currReview);
                    this.reviewList.put(currRecordTitle, newReviewlist);
                }
            }
            csvReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to get review data from csv file", e);
        }
    }

    public void writeToReviewCsv (Map<String, Review> newReviews) {
        File file = new File(REVIEW_DATA_FILENAME);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file, true);
            for (String title : newReviews.keySet()){
                Review currReview = newReviews.get(title);
                fileWriter.write("\n" + currReview.getTitle()  + ", "
                        + currReview.getReviewText()  + ", "
                        + currReview.getReviewedBy()  + ", "
                        + currReview.isLikeOrDislike());
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("something went wrong saving the new reviews!");
        }
    }

    public Map<String, List<Review>> getReviewList() {
        return reviewList;
    }
}
