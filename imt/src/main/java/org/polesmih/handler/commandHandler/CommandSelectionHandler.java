package org.polesmih.handler.commandHandler;

import lombok.SneakyThrows;
import org.polesmih.bot.settings.ConfigSettings;
import org.polesmih.bot.settings.Sender;
import org.polesmih.db.*;
import org.polesmih.bot.settings.DateFormatter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.sql.ResultSet;
import java.sql.Statement;

import static org.polesmih.bot.settings.MessagesConst.*;
import static org.polesmih.command.enums.Commands.*;


public class CommandSelectionHandler extends TelegramLongPollingBot {

    long chat_id;
    String message_text;
    private final static ConfigSettings settings = ConfigSettings.getInstance();


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
        update.getUpdateId();
        chat_id = update.getMessage().getChatId();
        message_text = update.getMessage().getText();

        Message message = update.getMessage();
        User from = message.getFrom();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());

        if (update.hasMessage() && update.getMessage().hasText()) {

            if (message_text.equals(START.getCommandType())) {
                execute(Sender.sendMessage(chat_id, "Привет, " + message.getFrom().getFirstName() + HELLO));


            } else if (message_text.equals(PREV.getCommandType())) {

                try {
                    String query = "SELECT date, user_imt FROM visits WHERE user_id = " + from.getId();

                    Statement statement = DbConnection.getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {

                        String date = resultSet.getString("date");
                        String imt = resultSet.getString("user_imt");
                        String history = String.format("%s %s - %s \n",
                                DateFormatter.formattingDateTime(date), "Ваш ИМТ был", imt);

                        execute(Sender.sendMessage(chat_id, history));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else if (message_text.equals(DONATE.getCommandType())) {
                execute(Sender.sendMessage(chat_id, DOG_SHELTER));


            } else if (message_text.equals(INFO.getCommandType())) {
                execute(Sender.sendMessage(chat_id, ABOUT_A));
                execute(Sender.sendMessage(chat_id, ABOUT_B));

            }

        }
    }

}
