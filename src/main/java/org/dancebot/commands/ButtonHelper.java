package org.dancebot.commands;

import java.util.List;

public interface ButtonHelper {
    List<String> onRecordStateButtons = List.of("Расписание", "Изменить");
    List<String> coachButtons = List.of("Записаться");
    List<String> editButtons = List.of("Записаться на занятие", "Удалить");
}
