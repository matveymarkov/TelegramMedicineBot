package bot.bot.helpers;


import bot.bot.models.UserModel;
import bot.bot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {
    @Autowired
    UserRepo userRepo;
    public UserHelper(){

        helper = this;
    }

    private static UserHelper helper ;

    public static void saveUser(UserModel u){
        helper.userRepo.save(u);
    }
    public  static UserModel finduser(String tgId){
        UserModel userModel = helper.userRepo.findUserModelByTgId(tgId);
        return userModel;
    }

}
