package org.example;

import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NamozTimeController {

    private MyTelegramBot myTelegramBot;

    public NamozTimeController(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
    }

    private String region = "Toshkent";



    public void today(Message message,String text) throws IOException {




        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId());
        InputFile inputFile = new InputFile();
        String temp = text.substring(1);
        if(temp.equals("Samarqand")){
            region=temp;
        } else if (temp.equals("Jizzax")) {
            region=temp;
        } else if (temp.equals("Qarshi")) {
            region=temp;
        } else if (temp.equals("Nukus")) {
            region=temp;
        } else if (temp.equals("Toshkent")) {
            region=temp;
        } else if (temp.equals("Andijon")) {
            region=temp;
        } else if (temp.equals("Guliston")) {
            region=temp;
        } else if (temp.equals("Buxoro")) {
            region=temp;
        } else if (temp.equals("Termiz")) {
            region=temp;
        }else if (temp.equals("Namangan")) {
            region=temp;
        }else if (temp.equals("Urganch")) {
            region=temp;
        }else if (temp.equals("Farg'ona")) {
            region=temp;
        }else if (temp.equals("Xiva")) {
            region=temp;
        }else if (temp.equals("Navoiy")) {
            region=temp;
        }

        String url = "https://islomapi.uz/api/present/day?region=" +region;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        JSONObject obj_JSONObject = new JSONObject(response.toString());


        String region = obj_JSONObject.getString("region");
        String date = obj_JSONObject.getString("date");
        String weekday = obj_JSONObject.getString("weekday");
        JSONObject times = obj_JSONObject.getJSONObject("times");
        String tong = times.getString("tong_saharlik");
        String quyosh = times.getString("quyosh");
        String peshin = times.getString("peshin");
        String asr = times.getString("asr");
        String shom = times.getString("shom_iftor");
        String hufton = times.getString("hufton");


        inputFile.setMedia("AgACAgIAAxkBAAMzYyYFCUov2F8qFRn6QkJV2rPMJbQAArG_MRuA8tBIHDMkw1yD23UBAAMCAANzAAMpBA");
        sendPhoto.setPhoto(inputFile);
        sendPhoto.setCaption(region + " shahri\n\n" + "☪" + date + "\n\n" +
                weekday + "\n\n" + "\uD83C\uDFD9Tong: " + tong + "\uD83D\uDD70\n\n"
                + "\uD83C\uDF05\tQuyosh: " + quyosh + "\uD83D\uDD70\n\n" + "☀\tPeshin: " + peshin + "\uD83D\uDD70\n\n"
                + "\uD83C\uDFDE\tAsr: " + asr + "\uD83D\uDD70\n\n" +
                "\uD83C\uDF06\tShom: " + shom + "\uD83D\uDD70\n\n" +
                "\uD83C\uDF03\tHufton: " + hufton + "\uD83D\uDD70\n\n");

/*
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(message.getChatId());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setText("Bosh Menuga Qaytish");*/

        sendPhoto.setReplyMarkup(InlineButton.keyboardMarkup(InlineButton.rowList(
                InlineButton.row(InlineButton.button("Samarqand","TSamarqand")
                        ,InlineButton.button("Jizzax","TJizzax"),
                        InlineButton.button("Qarshi","TQarshi")),
                InlineButton.row(InlineButton.button("Nukus","TNukus"),
                        InlineButton.button("Toshkent","TToshkent"),
                        InlineButton.button("Andijon","TAndijon")),
                InlineButton.row(InlineButton.button("Buxoro","TBuxoro"),
                        InlineButton.button("Guliston","TGuliston"),
                        InlineButton.button("Xiva","TXiva")),
                InlineButton.row(InlineButton.button("Namangan","TNamangan"),
                        InlineButton.button("Termiz","TTermiz"),
                        InlineButton.button("Urganch","TUrganch")),
                        InlineButton.row(InlineButton.button("Navoiy","TNavoiy"),
                                InlineButton.button("🔙 Back to Menu","menu")
        ))));


        myTelegramBot.send(sendPhoto);
      /*  myTelegramBot.send(editMessageText);*/

    }

}
