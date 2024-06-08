package org.example.output;

import org.example.model.Task;
import org.example.service.TaskService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OutputHandler {

    private static final DateTimeFormatter FORMAT_FOR_DATE = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final String ID_TASK = "ID задачи: ";
    private static final String TITLE = "Заголовок: ";
    private static final String DESCRIPTION = "Описание: ";
    private static final String DATE = "Дата создания: ";
    private final TaskService service;

    public OutputHandler(TaskService service) {
        this.service = service;
    }

    public void showAllTasks() {
        List<Task> tasks = service.allTasks();
        if (tasks.isEmpty()) {
            System.out.println("Ваш список задач пуст");
            return;
        }
        StringBuilder builder = new StringBuilder();
        LocalDateTime dateTimeCreation;
        int maxLength;
        String tempId;
        String tempTitle;
        String tempDescrip;
        String tempDate;
        boolean flag = true;
        for (Task task : tasks) {

            dateTimeCreation = task.getDateTimeCreation();
            tempId = ID_TASK + task.getId();
            tempTitle = TITLE + task.getName();
            tempDescrip = DESCRIPTION + task.getDescription();
            tempDate = DATE + dateTimeCreation.format(FORMAT_FOR_DATE);
            maxLength = getMaxLengthInString(tempId, tempTitle, tempDescrip, tempDate);

            while (flag) {
                builder.append("*".repeat(maxLength + 2)).append("\n");
                flag = false;
            }
            builder.append(tempId).append(" ".repeat(maxLength - tempId.length())).append(" *\n");
            builder.append(tempTitle).append(" ".repeat(maxLength - tempTitle.length())).append(" *\n");
            builder.append(tempDescrip).append(" ".repeat(maxLength - tempDescrip.length())).append(" *\n");
            builder.append(tempDate).append(" ".repeat(maxLength - tempDate.length())).append(" *\n");
            builder.append("*".repeat(maxLength + 2)).append("\n");
        }
        System.out.println(builder);
    }

    private int getMaxLengthInString(String... array) {
        int max = 0;
        for (String str : array) {
            if (str != null && str.length() > max) {
                max = str.length();
            }
        }
        return max;
    }
}
