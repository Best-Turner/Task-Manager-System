package org.example.menu;

public class Menu {

    public final static String HELLO = "Добро пожаловать в программу!!";
    public static final String ENTER_NAME = "Введите название задачи";
    public static final String ENTER_DESCRIPTION = "Введите описание задачи";
    public static final String ENTER_ID_FOR_DELETE = "Введите ID задачи которую хотите удалить";
    public static final String ENTER_ID_FOR_GET = "Введите ID задачи которую хотите получить";
    public static final String COMMAND = """
            1. Список задач.
            2. Создать новую задачу.
            3. Удалить задачу.
            """;

    public void printCommand(String printToCommand) {
        System.out.println(printToCommand);
    }

}
