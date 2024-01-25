package bot.bot.repos;

import bot.bot.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserModel,Long> {
    UserModel findUserModelByTgId(String id);
}
