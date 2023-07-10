package totalPackage;

import totalPackage.dependency.ReviewComponent;
import totalPackage.dependency.ReviewModule;
import totalPackage.io.InputHandler;
import totalPackage.service.ReviewService;

public class App {

    //need to set up DI and Dagger
//    private  Reviewer reviewer = new Reviewer(); // probably need a better place for this
//    private ReviewService  reviewService = new ReviewService(reviewer);
//
//    private InputHandler inputHandler = new InputHandler();
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        String userResponse = "";
        do {
            System.out.println(userResponse);
            System.out.println(MenuOption.renderMenu());
            userResponse = handleUserRequest();
        } while(userResponse != MenuOption.QUIT.toString());
    }

    private String handleUserRequest() {
        ReviewComponent dagger = null; //.create();   //dagger componenet
        //ReviewModule daggerModule
        InputHandler inputHandler  = dagger.provideInputhandler();
        Reviewer reviewer = dagger.provideReviewer();
        ReviewService  reviewService = dagger.provideReviewService();

        //need to hold name and store for when they log or search for their reviews
        System.out.println("Please enter your name> ");
        String name = inputHandler.getInput();
        reviewer.setName(name);

        System.out.println("Enter an option> ");
        int menuOptionNum = inputHandler.getIntInput();

        MenuOption option = MenuOption.fromOptionNum(menuOptionNum);
        switch (option) {
            case QUIT:
                return option.toString();
            case NEW_REVIEW:
                return reviewService.addReview();
            case SEARCH_FOR_REVIEW:
                return "";
            case ALL_TV:
                return "";
            case ALL_MOVIES:
                return "";
            default:
                return "Unimplemented Operation!";

        }
    }
}
