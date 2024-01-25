package bot.bot.repos;

import bot.bot.helpers.DoctorEnum;
import bot.bot.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<BookModel,Long> {
    List<BookModel> findBookModelByDoctorEnum(DoctorEnum doctorEnum );

    //  List<BookModel> findBookModelByTime(String time );
    BookModel findBookModelByTgId(String tgid);



}
