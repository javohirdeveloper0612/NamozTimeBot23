package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MainController {

    private MyTelegramBot myTelegramBot;

    public MainController(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
    }

    public void handle(Message message) {


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Assalamu 'alekum aziz musulmon 👳‍♂️ aka - ukalar va muslima 🧕 opa - singillar.\n" +
                "\n" +
                "Botga xush kelibsiz! Ushbu bot butun dunyo musulmonlari uchun!\n" +
                "\n" +
                "Botimiz namoz vaqtlarini aniqlashga sizga yordamchi bo'ladi degan umiddamiz!\n" +
                "\n" +
                "Eslatma!\n" +
                "Botdagi namoz vaqtlaridan ko'ra sizga yaqinda joylashgan Jome' masjidlaridagi namoz vaqtlariga e'tibor qaratgan afzalroq. Chunki kichik farqlar bo'lishi mumkin. Vaqtlar chet el  saytidan yer yuzidagi joylashuvga qarab olinmoqda olinmoqda!\n" +
                "\n");


        sendMessage.setReplyMarkup(InlineButton.keyboardMarkup(InlineButton.rowList(
                InlineButton.row(InlineButton.button("☪Namoz Vaqtlari", "time"),
                        InlineButton.button("Biz bilan aloqa", "aloqa")),
                InlineButton.row(InlineButton.button("Sozlamalar", "setting"))
        )));

        myTelegramBot.send(sendMessage);


    }
}

