package bot.bot.commands;

import bot.bot.commands.WorkerCommand;
import bot.bot.helpers.DoctorEnum;
import bot.bot.helpers.ReviewHelper;
import bot.bot.models.ReviewModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ChooseDoctorForReviewCommand implements WorkerCommand {
    @Override
    public SendMessage start(Update update) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        List<String> enums = new ArrayList<>();
        enums.add(DoctorEnum.ALLERGOLOG.toString());
        enums.add(DoctorEnum.HIRURG.toString());
        enums.add(DoctorEnum.LOR.toString());
        enums.add(DoctorEnum.OKULIST.toString());
        enums.add(DoctorEnum.TERAPEVT.toString());
        enums.add(DoctorEnum.GINEKOLOG.toString());
        if (update.getMessage().getText().equals("/start")) {
            return sendDefaultMessage(update);
        }

        for (String anEnum : enums) {
            if (update.getMessage().getText().equals(anEnum)) {
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                KeyboardRow keyboardRow = new KeyboardRow();
                sendMessage.setText("Нажмите на кнопку для оценки");
                keyboardRow.add(new KeyboardButton("1"));
                keyboardRow.add(new KeyboardButton("2"));
                keyboardRow.add(new KeyboardButton("3"));
                keyboardRow.add(new KeyboardButton("4"));
                keyboardRow.add(new KeyboardButton("5"));
                keyboardRow.add(new KeyboardButton("6"));
                keyboardRow.add(new KeyboardButton("7"));
                keyboardRow.add(new KeyboardButton("8"));
                keyboardRow.add(new KeyboardButton("9"));
                keyboardRow.add(new KeyboardButton("10"));
                replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
                sendMessage.setReplyMarkup(replyKeyboardMarkup);


            }
        }
        return sendMessage;
    }


    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
