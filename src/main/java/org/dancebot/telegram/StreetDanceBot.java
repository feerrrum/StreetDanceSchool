package org.dancebot.telegram;


import org.dancebot.commands.CommandResult;
import org.dancebot.commands.TextCommandHandler;
import org.dancebot.users.UserProvider;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class StreetDanceBot extends TelegramLongPollingBot {

    private final String name;
    private TextCommandHandler textHandler = null;
    private TextCommandHandler buttonHandler = null;

    public StreetDanceBot(String token, String name){
        super(token);
        this.name = name;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var message = update.getMessage();
            var chatId = update.getMessage().getChatId();
            var session = UserProvider.getInstance().findUserById(new TelegramUserId(chatId));
            if (textHandler != null) {
                var result = textHandler.processCommand(session, message.getText());
                response(result, chatId);
            }
        }
        if (update.hasCallbackQuery()) {
            var callbackQuery = update.getCallbackQuery();
            var chatId = callbackQuery.getMessage().getChatId();
            var session = UserProvider.getInstance().findUserById(new TelegramUserId(chatId));
            var command = callbackQuery.getData();
            if (buttonHandler != null) {
                var result = buttonHandler.processCommand(session, command);
                response(result, chatId);
            }
        }
    }

    private void response(CommandResult result, Long chayId) {
        SendMessage response = new SendMessage();
        response.setChatId(chayId);
        response.setText(result.getResult());
        if (result.hasButtons()) {
            ReplyKeyboardMarkup markupInline = getReplyKeyboardMarkup(result);
            response.setReplyMarkup(markupInline);
        }
        try {
            execute(response);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private static ReplyKeyboardMarkup getReplyKeyboardMarkup(CommandResult result) {
        ReplyKeyboardMarkup markupReply = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        for (String text : result.getButtons()) {
            row.add(new KeyboardButton(text));
        }
        rows.add(row);
        markupReply.setKeyboard(rows);
        return markupReply;
    }
    @Override
    public String getBotUsername() {
        return name;
    }
    public void addTextHandler(TextCommandHandler textHandler) {
        this.textHandler = textHandler;
    }

    public void addButtonHandler(TextCommandHandler buttonHandler) {
        this.buttonHandler = buttonHandler;
    }
}
