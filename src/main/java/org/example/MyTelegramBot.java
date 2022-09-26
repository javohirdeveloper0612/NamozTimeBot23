package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class MyTelegramBot extends TelegramLongPollingBot {

    private MainController mainController;
    private NamozTimeController namozTimeController;

    public MyTelegramBot() {
        this.mainController = new MainController(this);
        this.namozTimeController = new NamozTimeController(this);
    }

    @Override
    public void onUpdateReceived(Update update) {

        try {

            if (update.hasMessage()) {
                Message message = update.getMessage();

                if (message.hasText()) {
                    String  text = message.getText();
                    if (text.equals("/start") || text.equals("start")){

                        mainController.handle(message);
                    }
                } else {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId());
                    sendMessage.setText("Unknown");
                    send(sendMessage);
                }

            } else if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                Message message = callbackQuery.getMessage();
                String query = callbackQuery.getData();
                  if (query.equals("time") || query.startsWith("T")) {
                    namozTimeController.today(message,query);
                }else if (query.equals("menu")){
                      mainController.handle(message);
                  }
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(EditMessageText sms) {
        try {
            execute(sms);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotUsername() {
        return "namozTimeUZB_Bot";
    }

    @Override
    public String getBotToken() {
        return "5614411364:AAEXAGePZg_CpKZcb4HwK6xgL5swIaWAqBM";
    }
}
