package bot.bot.commands;

import bot.bot.helpers.UserHelper;
import bot.bot.models.UserModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

@Component
public class LoginCommand implements WorkerCommand {
    @Override
    public SendMessage start(Update update) {
        if (!update.getMessage().getText().equals("Log in")&&
                !update.getMessage().getText().equals("Оставить мое имя")&&
                !update.getMessage().getText().equals("Остаться анонимом")) {
            return null;
        }
        SendMessage sendMessage = new SendMessage();
      sendMessage.setText("Выберите действие");
      sendMessage.setChatId(update.getMessage().getChatId().toString());

        if (update.getMessage().getText().equals("Log in")) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton("Оставить мое имя"));
            keyboardRow.add(new KeyboardButton("Остаться анонимом"));
            sendMessage.setText("Выберите действие");

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }

        if(update.getMessage().getText().equals("Остаться анонимом")){
            UserModel userModel = new UserModel();
            userModel.setUsername(update.getMessage().getFrom().getUserName());
            userModel.setTgId(update.getMessage().getFrom().getId().toString());
            sendMessage.setText("Пользователь сохранен");

            UserHelper.saveUser(userModel);
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton("Записаться к врачу"));
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
            sendMessage.setReplyMarkup(replyKeyboardMarkup);

        }
        if (update.getMessage().getText().equals("Оставить мое имя")){
            UserModel userModel = new UserModel();
            userModel.setUsername(update.getMessage().getFrom().getUserName());
            userModel.setTgId(update.getMessage().getFrom().getId().toString());
            userModel.setName(update.getMessage().getFrom().getFirstName());
            sendMessage.setText("Пользователь сохранен");
            UserHelper.saveUser(userModel);
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton("Записаться к врачу"));
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }

        return sendMessage;

    }
    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
