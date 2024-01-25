package bot.bot.repos;

import bot.bot.models.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<ReviewModel,Long> {
}
