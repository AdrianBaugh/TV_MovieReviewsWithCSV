package totalPackage.io;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

public class InputHandler {

    private Scanner scanner = new Scanner(new InputStreamReader(System.in, Charset.forName("UTF-8")));


    public int getIntInput(){
        String menuOptionNum = scanner.nextLine();

        return Integer.parseInt(menuOptionNum);
    }

    public String getInput() {
        return scanner.nextLine();
    }
}
