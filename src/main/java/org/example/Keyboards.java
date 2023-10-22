package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboards {

    public void mainKeyboard(SendMessage response){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Записаться на занятие");
        keyboardRowList.add(row);
        row = new KeyboardRow();
        row.add("Личный кабинет");
        keyboardRowList.add(row);

        keyboardMarkup.setKeyboard(keyboardRowList);
        response.setReplyMarkup(keyboardMarkup);
    }

    public void backKeyboard(SendMessage response) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Назад");
        keyboardRowList.add(row);

        keyboardMarkup.setKeyboard(keyboardRowList);
        response.setReplyMarkup(keyboardMarkup);
    }

    public void userKeyboard(SendMessage response) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Расписание");
        keyboardRowList.add(row);
        row = new KeyboardRow();
        row.add("Абонемент");
        keyboardRowList.add(row);

        keyboardMarkup.setKeyboard(keyboardRowList);
        response.setReplyMarkup(keyboardMarkup);
    }

    public void paymentKeyboard(SendMessage response) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Продлить");
        keyboardRowList.add(row);
        row = new KeyboardRow();
        row.add("Назад");
        keyboardRowList.add(row);

        keyboardMarkup.setKeyboard(keyboardRowList);
        response.setReplyMarkup(keyboardMarkup);
    }
}
