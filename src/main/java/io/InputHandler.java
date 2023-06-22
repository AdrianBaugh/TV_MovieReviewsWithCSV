package io;

import java.util.Scanner;

public class InputHandler {

    private Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }
    public int getIntInput(){
        String menuOptionNum = scanner.nextLine();

        return Integer.parseInt(menuOptionNum);
    }
}
