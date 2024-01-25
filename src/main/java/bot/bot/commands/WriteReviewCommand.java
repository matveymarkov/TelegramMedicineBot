package bot.bot.commands;

import bot.bot.helpers.ReviewHelper;
import bot.bot.models.ReviewModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Collections;

@Component
public class WriteReviewCommand implements WorkerCommand {
    @Override
    public SendMessage start(Update update) {


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());


        if (!update.getMessage().getText().equals("10") &&
                !update.getMessage().getText().equals("9") &&
                !update.getMessage().getText().equals("8") &&
                !update.getMessage().getText().equals("7") &&
                !update.getMessage().getText().equals("6") &&
                !update.getMessage().getText().equals("5") &&
                !update.getMessage().getText().equals("4") &&
                !update.getMessage().getText().equals("3") &&
                !update.getMessage().getText().equals("2") &&
                !update.getMessage().getText().equals("1")) {
            return sendDefaultMessage(update);
        }
        ReviewModel reviewModel = new ReviewModel();


        reviewModel.setTgId(update.getMessage().getFrom().getId().toString());
        reviewModel.setName(update.getMessage().getFrom().getFirstName());
        reviewModel.setUsername(update.getMessage().getFrom().getLastName());
        reviewModel.setMark(update.getMessage().getText());
        sendMessage.setText("Cпасибо за оценку");
        ReviewHelper.saveReview(reviewModel);


        return sendMessage;

    }


    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
