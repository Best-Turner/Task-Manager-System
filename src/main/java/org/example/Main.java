package org.example;


import org.example.db.ConnectionManager;
import org.example.db.DatabaseConnection;
import org.example.input.IncomingHandler;
import org.example.menu.Menu;
import org.example.output.OutputHandler;
import org.example.service.TaskService;
import org.example.storage.TaskRepository;
import org.example.util.ReadingProperties;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Menu menu = new Menu();
        ReadingProperties readingProperties = new ReadingProperties("application.properties");
        ConnectionManager connectionManager = new DatabaseConnection(readingProperties);
        TaskRepository repository = new TaskRepository(connectionManager);
        TaskService service = new TaskService(repository);
        Scanner scanner = new Scanner(System.in);
        OutputHandler outputHandler = new OutputHandler(service);
        IncomingHandler incomingHandler = new IncomingHandler(scanner);
        boolean flag = true;

        menu.printCommand(Menu.HELLO);
        while (flag) {
            Thread.sleep(1000);
            menu.printCommand(Menu.COMMAND);
            String number = incomingHandler.readingCommandNumber();
            switch (number) {
                case "1" -> outputHandler.showAllTasks();
                case "2" -> {
                    menu.printCommand(Menu.ENTER_NAME);
                    String handler = incomingHandler.inputText();
                    menu.printCommand(Menu.ENTER_DESCRIPTION);
                    String description = incomingHandler.inputText();
                    service.saveTask(handler, description);
                    System.out.println("Ok!");
                }
                case "3" -> {
                    menu.printCommand(Menu.ENTER_ID_FOR_DELETE);
                    int id = Integer.valueOf(incomingHandler.readingCommandNumber());
                    try {
                        service.deleteTaskById(id);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    System.out.println("Ok!");
                }
                case "6881" -> {
                    flag = false;
                }
            }
        }
        scanner.close();
    }
}
