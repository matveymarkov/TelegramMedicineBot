package bot.bot.helpers;

import bot.bot.models.ReviewModel;
import bot.bot.models.UserModel;
import bot.bot.repos.ReviewRepo;
import bot.bot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewHelper {
    @Autowired
    ReviewRepo reviewRepo;
    public ReviewHelper(){

        reviewHelper = this;
    }

    private static ReviewHelper reviewHelper ;

    public static void saveReview(ReviewModel r){
        reviewHelper.reviewRepo.save(r);
    }


}


