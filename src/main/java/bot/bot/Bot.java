package bot.bot;

import bot.bot.commands.*;
import bot.bot.commands.bookcommand.*;
import bot.bot.helpers.ReviewHelper;
import bot.bot.models.BookModel;
import bot.bot.models.ReviewModel;
import bot.bot.repos.BookRepo;
import bot.bot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {
    @Autowired
    UserRepo userRepo;
    @Autowired
    BookRepo bookRepo;

    @Override
    public void onUpdateReceived(Update update) {

        KeyboardRow keyboardRow = new KeyboardRow();
        List<BookModel> bookModelList = new ArrayList<>();
        if (userRepo.findUserModelByTgId(update.getMessage().getFrom().getId().toString()) == null)
            keyboardRow.add(new KeyboardButton("Log in"));
        if (bookRepo.findBookModelByTgId(update.getMessage().getFrom().getId().toString()) != null)
            keyboardRow.add(new KeyboardButton("Поставить оценку"));
        keyboardRow.add(new KeyboardButton("Записаться к врачу"));

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("Выберите действие");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        List<WorkerCommand> list = new ArrayList<>();
        list.add(new BookCommand());
        list.add(new LoginCommand());
        list.add(new ReviewCommand());
        list.add(new WriteReviewCommand());
        list.add(new TerapevtBookCommand());
        list.add(new OkulistBookCommand());
        list.add(new LorBookCommand());
        list.add(new HirurgBookCommand());
        list.add(new GinekologBookCommand());
        list.add(new AllergologBookCommand());
        list.add(new ChooseTime());
        list.add(new ChooseDoctorForReviewCommand());


        SendMessage s;
        for (WorkerCommand w : list) {
            if ((s = w.start(update)) != null) {

                sendMessage = s;
                break;
            }
        }


        try {
            execute(sendMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public String getBotToken() {
        return " ";
    }

    @Override
    public String getBotUsername() {
        return "spring_medbot";
    }
}
