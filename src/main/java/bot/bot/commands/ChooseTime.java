package bot.bot.commands;

import bot.bot.helpers.DoctorHelper;
import bot.bot.helpers.TimeControl;
import bot.bot.helpers.UserHelper;
import bot.bot.models.BookModel;
import bot.bot.models.UserModel;
import bot.bot.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
@Component
public class ChooseTime implements WorkerCommand{

   @Autowired
   BookRepo bookRepo;
    @Override
    public SendMessage start(Update update) {

        TimeControl timeControl = new TimeControl();
        List<String> list = timeControl.getTimes();
        boolean ifThisCommand = false;
        for (String str : list) {
            if (update.getMessage().getText().equals(str)) {
                ifThisCommand = true;
            }
        }
        if (!ifThisCommand) {
            return null;
        }
        BookModel bookModel = new BookModel();
        UserModel userModel = new UserModel();
        SendMessage sendMessage = new SendMessage();
       // if (bookRepo.findBookModelByDoctorEnum(userModel.getDoctorEnum())!=null&&bookRepo.findBookModelByTime(update.getMessage().getText())!=null) {;
        //    sendMessage.setChatId(update.getMessage().getChatId());
         //   sendMessage.setText("Извините,но это время уже занято");

       // } else {

            bookModel.setTime(update.getMessage().getText().toString());

            userModel = UserHelper.finduser(update.getMessage().getFrom().getId().toString());
            bookModel.setTgId(update.getMessage().getFrom().getId().toString());
            bookModel.setDoctorEnum(userModel.getDoctorEnum());

            DoctorHelper.save(bookModel);

            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Вы успешно записались к врачу");
      //  }
        return  sendMessage;
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
