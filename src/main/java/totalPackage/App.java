package totalPackage;

import totalPackage.io.InputHandler;
import totalPackage.service.ReviewService;

public class App {

    private  Reviewer userName; // probably need a better place for this

    //need to set up DI and Dagger
    private ReviewService  reviewService = new ReviewService();
    private InputHandler inputHandler = new InputHandler();
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
        //need to hold name and store for when they log or search for their reviews
        System.out.println("Please enter your name> ");
        this.userName.setName( inputHandler.getInput());

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
