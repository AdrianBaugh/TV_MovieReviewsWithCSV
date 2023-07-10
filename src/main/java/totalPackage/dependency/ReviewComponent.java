package totalPackage.dependency;

import dagger.Component;
import totalPackage.service.ReviewService;

@Component(modules = ReviewModule.class)
public interface ReviewComponent {


    ReviewService provideReviewService();
}
