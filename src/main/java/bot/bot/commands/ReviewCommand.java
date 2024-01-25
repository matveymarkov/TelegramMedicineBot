package bot.bot.commands;

import bot.bot.helpers.DoctorEnum;
import bot.bot.helpers.ReviewHelper;
import bot.bot.models.ReviewModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewCommand implements WorkerCommand {
    @Override
    public SendMessage start(Update update) {


        if (!update.getMessage().getText().equals("Поставить оценку")) {
            return null;
        }
        SendMessage sendMessage = new SendMessage();
        KeyboardRow k1 = new KeyboardRow();

        sendMessage.setChatId(update.getMessage().getChatId().toString());

        sendMessage.setText("Выберите врача которому хотите поставить оценку");

        DoctorEnum doctorEnum;
        k1.add(new KeyboardButton(DoctorEnum.TERAPEVT.toString()));
        k1.add(new KeyboardButton(DoctorEnum.HIRURG.toString()));

        KeyboardRow k2 = new KeyboardRow();
        k2.add(new KeyboardButton(DoctorEnum.OKULIST.toString()));
        k2.add(new KeyboardButton(DoctorEnum.LOR.toString()));

        KeyboardRow k3 = new KeyboardRow();
        k3.add(new KeyboardButton(DoctorEnum.GINEKOLOG.toString()));
        k3.add(new KeyboardButton(DoctorEnum.ALLERGOLOG.toString()));

        List<KeyboardRow> list = new ArrayList<>();
        list.add(k1);
        list.add(k2);
        list.add(k3);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(list);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);


        return sendMessage;
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
