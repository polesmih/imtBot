package org.polesmih.bot;

import lombok.SneakyThrows;
import org.polesmih.bot.settings.ConfigSettings;
import org.polesmih.bot.settings.Sender;
import org.polesmih.db.WriteUser;
import org.polesmih.handler.commandHandler.CommandSelectionHandler;
import org.polesmih.handler.commandHandler.CommandTypes;
import org.polesmih.handler.ImtCount;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;

import static org.polesmih.bot.settings.MessagesConst.*;
import static org.polesmih.command.BotCommands.LIST_OF_COMMAND;


public class Bot extends TelegramLongPollingBot {
    CommandTypes commandType = new CommandTypes();
    CommandSelectionHandler commandHandler = new CommandSelectionHandler();
    private final static ConfigSettings settings = ConfigSettings.getInstance();
    String message_text;
    Long chat_id;


    public void init() throws TelegramApiException {
        this.execute(new SetMyCommands(LIST_OF_COMMAND, new BotCommandScopeDefault(), null));
    }

    @Override
    public String getBotUsername() {
        return settings.getUserName();
    }

    @Override
    public String getBotToken() {
        return settings.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            message_text = update.getMessage().getText();
            chat_id = update.getMessage().getChatId();

            Message message = update.getMessage();
            User from = message.getFrom();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());

            if (commandType.types().contains(message_text)) {
                commandHandler.onUpdateReceived(update);

            } else if (message_text.contains(" ")) {

                String[] weightAntHeight = update.getMessage().getText().split(" ");

                if (weightAntHeight.length == 2
                        && weightAntHeight[0].matches("\\d+")
                        && weightAntHeight[1].matches("\\d+")) {

                    String resultImt = String.format("%.1f", ImtCount.imt(weightAntHeight[0], weightAntHeight[1]));
                    String resultWithDescription = ImtCount.description(weightAntHeight[0], weightAntHeight[1]);

                    WriteUser.writeUserIntoDb(LocalDateTime.now().withNano(0),
                            from.getId(), from.getFirstName()
                            , resultImt
                    );
                    sendMessage.setText(resultWithDescription);
                    execute(sendMessage);

                } else {
                    execute(Sender.sendMessage(chat_id, UNKNOWN + INSTRUCTION));
                }
            }

            else {
                execute(Sender.sendMessage(chat_id, UNKNOWN + INSTRUCTION));
            }

        }

    }
}
