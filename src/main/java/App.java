import IO.InputService;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

public class App {

    private Scanner scanner = new Scanner(new InputStreamReader(System.in, Charset.forName("UTF-8")));
    private InputService userInput = new InputService(scanner);
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
        System.out.println("Enter an option> ");
        //String menuOptionNum = scanner.nextLine();
        int menuOptionNum = userInput.getIntInput();

        MenuOption option = MenuOption.fromOptionNum(menuOptionNum);
        switch (option) {
            case QUIT:
                return "";
            case NEW_REVIEW:
                return "";
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
