package org.dancebot.commands;

import java.util.List;

public interface ButtonHelper {
    public static List<String> backButtons = List.of("Назад");
    public static List<String> notOnRecordStateButtons = List.of("Записаться на занятие");
    public static List<String> onRecordStateButtons = List.of("Расписание", "Изменить");
    public static List<String> coachButtons = List.of("Запись");
    public static List<String> editButtons = List.of("Записаться на занятие", "Удалить");
}
