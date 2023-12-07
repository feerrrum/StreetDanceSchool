package org.dancebot.commands;

import java.util.List;

public interface ButtonHelper {
    public static List<String> backButtons = List.of("Назад");
    public static List<String> newBeeStateButtons = List.of("Записаться на занятие");
    public static List<String> onRecordStateButtons = List.of("Записаться на занятие", "Личный кабинет");
}
