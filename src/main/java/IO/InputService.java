package IO;

import java.util.Scanner;

public class InputService {

    private Scanner scanner;

    public InputService(Scanner scanner) {
        this.scanner = scanner;
    }
    public int getIntInput(){
        String menuOptionNum = scanner.nextLine();

        return Integer.parseInt(menuOptionNum);
    }
}
