package org.dancebot.commands;

import java.util.List;

public interface ButtonHelper {
    public static List<String> onRecordStateButtons = List.of("Расписание", "Изменить");
    public static List<String> coachButtons = List.of("Запись");
    public static List<String> editButtons = List.of("Записаться на занятие", "Удалить");
}
