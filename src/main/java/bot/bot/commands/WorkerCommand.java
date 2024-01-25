package bot.bot.commands;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public interface WorkerCommand {
    public SendMessage start(Update update);

    public SendMessage sendDefaultMessage(Update update);
}
