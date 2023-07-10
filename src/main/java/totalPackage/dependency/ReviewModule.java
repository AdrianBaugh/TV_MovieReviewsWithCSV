package totalPackage.dependency;

import dagger.Module;
import dagger.Provides;
import totalPackage.Reviewer;
import totalPackage.io.InputHandler;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

@Module
public class ReviewModule {

    @Provides
    InputHandler provideInputHandler() {
        Scanner scanner  = new Scanner(new InputStreamReader(System.in, Charset.forName("UTF-8")));
        return new InputHandler(scanner);
    }

    @Provides
    Reviewer provideReviewer() {
        return new Reviewer(String name);
    }
}
