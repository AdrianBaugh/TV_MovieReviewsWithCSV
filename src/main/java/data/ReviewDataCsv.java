package data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class ReviewDataCsv {

    private static final String REVIEW_DATA_RESOURCE = "reviewData.csv";

    private static final String REVIEW_DATA_FILENAME = System.getProperty("user.home") + "/.TV_MovieReviewsFromCSV/" + REVIEW_DATA_RESOURCE;

    private Map<String, Review> reviewList;

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

                if (record.getRecordNumber() == 1) {
                    continue;
                }

                if (record.get(1).isEmpty()) {
                    this.reviewList.put(record.get(0), (new Review(record.get(0), ", ", ", ", ", ")));
                } else {
                    this.reviewList.put(record.get(0), (new Review(record.get(0), record.get(1), record.get(2), record.get(3))));
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

    public Map<String, Review> getReviewList() {
        return reviewList;
    }
}
