package bot.bot.commands.bookcommand;

import bot.bot.commands.WorkerCommand;
import bot.bot.helpers.DoctorEnum;
import bot.bot.helpers.DoctorHelper;
import bot.bot.helpers.UserHelper;
import bot.bot.models.UserModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class TerapevtBookCommand implements WorkerCommand {
    @Override
    public SendMessage start(Update update) {
        if (!update.getMessage().getText().equals("Терапевт")) {
            return null;
        }
        UserModel userModel = UserHelper.finduser(update.getMessage().getFrom().getId().toString());
        userModel.setDoctorEnum(DoctorEnum.TERAPEVT);
        UserHelper.saveUser(userModel);
        return sendDefaultMessage(update);
    }
    @Override
    public SendMessage sendDefaultMessage(Update update) {
      SendMessage sendMessage = new SendMessage();
      sendMessage.setChatId(update.getMessage().getChatId().toString());
      sendMessage.setText("Выберите подходящее время");

            List<String> list = DoctorHelper.getFreeTimes(DoctorEnum.TERAPEVT);
            KeyboardRow k1 = new KeyboardRow();
            k1.add(new KeyboardButton(list.get(0)));
            k1.add(new KeyboardButton(list.get(1)));
            List<KeyboardRow> list1 = new ArrayList<>();
            list1.add(k1);

            KeyboardRow k2 = new KeyboardRow();
            if (list.size()>2){
                for (int i = 2; i <list.size() ; i++) {
                    k2.add(new KeyboardButton(list.get(i)));
                }
               list1.add(k2);
            }
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setKeyboard(list1);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            return sendMessage;
    }
}



