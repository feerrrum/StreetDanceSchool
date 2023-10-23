
package org.example.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboards {
    private final String message;
    public Keyboards(String m) {message = m;}
    public void getKeyboard(SendMessage response) {
        switch (message) {
            case ("/start"):
                mainKeyboard(response);
                break;
            case ("���������� �� �������"):
                backKeyboard(response);
                break;
            case ("������ �������"):
                userKeyboard(response);
                break;
            case ("����������"):
                backKeyboard(response);
                break;
            case ("���������"):
                paymentKeyboard(response);
                break;
            case ("��������"):
                backKeyboard(response);
                break;
            case ("�����"):
                mainKeyboard(response);
                break;
        }
    }

    private void mainKeyboard(SendMessage response){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("���������� �� �������");
        keyboardRowList.add(row);
        row = new KeyboardRow();
        row.add("������ �������");
        keyboardRowList.add(row);

        keyboardMarkup.setKeyboard(keyboardRowList);
        response.setReplyMarkup(keyboardMarkup);
    }

    private void backKeyboard(SendMessage response) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("�����");
        keyboardRowList.add(row);

        keyboardMarkup.setKeyboard(keyboardRowList);
        response.setReplyMarkup(keyboardMarkup);
    }

    private void userKeyboard(SendMessage response) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("����������");
        keyboardRowList.add(row);
        row = new KeyboardRow();
        row.add("���������");
        keyboardRowList.add(row);

        keyboardMarkup.setKeyboard(keyboardRowList);
        response.setReplyMarkup(keyboardMarkup);
    }

    private void paymentKeyboard(SendMessage response) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("��������");
        keyboardRowList.add(row);
        row = new KeyboardRow();
        row.add("�����");
        keyboardRowList.add(row);

        keyboardMarkup.setKeyboard(keyboardRowList);
        response.setReplyMarkup(keyboardMarkup);
    }
}