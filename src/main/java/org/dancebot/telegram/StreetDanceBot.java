package org.dancebot.telegram;


import org.dancebot.commands.CommandResult;
import org.dancebot.commands.TextCommandHandler;
import org.dancebot.database.DatabaseHandler;
import org.dancebot.users.UserProvider;
import org.dancebot.users.UserSession;
import org.dancebot.users.UserState;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StreetDanceBot extends TelegramLongPollingBot {

    private final String name;
    private TextCommandHandler textHandler = null;
    private TextCommandHandler buttonHandler = null;
    private DatabaseHandler dbHandler = null;

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

            try {
                if (dbHandler.isOnRecord(session.getId())) {
                    session.setState(UserState.ON_RECORD);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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

        if (result.getMultipleResults() != null) {
            int i = 1;
            for (String text : result.getMultipleResults()) {
                response.setText(text);
                if (result.hasButtons()) {
                    result.setResult(String.valueOf(i));
                    InlineKeyboardMarkup markupInline = getInlineKeyBoardMarkup(result);
                    response.setReplyMarkup(markupInline);
                }
                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                i++;
            }
        } else {
            if (result.hasButtons()) {
                ReplyKeyboardMarkup markupReply = getReplyKeyboardMarkup(result);
                response.setReplyMarkup(markupReply);
            }
            response.setText(result.getResult());
            try {
                execute(response);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
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
    private static InlineKeyboardMarkup getInlineKeyBoardMarkup(CommandResult result) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        for (String text : result.getButtons()) {
            var button = new InlineKeyboardButton();
            button.setText(text);
            button.setCallbackData(result.getResult());
            rowInline.add(button);
        }
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
    @Override
    public String getBotUsername() {
        return name;
    }
    public void addDbHandler(DatabaseHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
    public void addTextHandler(TextCommandHandler textHandler) {
        this.textHandler = textHandler;
    }

    public void addButtonHandler(TextCommandHandler buttonHandler) {
        this.buttonHandler = buttonHandler;
    }
}