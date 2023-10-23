package org.example.commands;

public class ResponseHandler {
    private final String message;
    public ResponseHandler(String m){message = m;}
    public String setResponse() {
        return switch (message) {
            case ("/start") -> "Это бот для студии танцев";
            case ("Записаться на занятие") -> "Список педагогов:";
            case ("Личный кабинет") -> "ичный кабинет";
            case ("Расписание") -> "*календарь с расписанием*";
            case ("Абонемент") -> "У вас осталось 0 занятий";
            case ("Продлить") -> "Оплатить";
            case ("Назад") -> "Назад";
            default -> "what??";
        };
    }
}