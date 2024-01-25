package bot.bot.helpers;

import bot.bot.models.BookModel;
import bot.bot.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorHelper {
    @Autowired
    BookRepo bookRepo;

    public DoctorHelper() {
        doctorHelper=this;

    }

    private static DoctorHelper doctorHelper = null;
    public static void save(BookModel b){
        doctorHelper.bookRepo.save(b);
    }
    public static List<String> getFreeTimes(DoctorEnum doctorEnum){
        TimeControl timeControl = new TimeControl();
        List<BookModel> bookModelList = doctorHelper.bookRepo.findBookModelByDoctorEnum(doctorEnum);
        List<String> freetimes = new ArrayList<>();
        freetimes = timeControl.getTimes();

        List<String> list = new ArrayList<>();
        for (BookModel b: bookModelList){
            for (String str:freetimes){
                if (b.getTime().equals(str)){
                    list.add(b.getTime());
                }
            }
        }
        freetimes.remove(list);
        return freetimes;

    }
}
