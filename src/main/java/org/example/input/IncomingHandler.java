package org.example.input;

import java.util.Scanner;

public class IncomingHandler {

    private Scanner scanner;

    public IncomingHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readingCommandNumber() {
        String inputCommand = scanner.nextLine();
        if (inputCommand.isBlank() || !inputCommand.matches("\\S+")) {
            System.out.println("Вы ввели не числовое значение");
            return null;
        }
        return inputCommand;
    }

    public String inputText() {
        String inputText = scanner.nextLine();

        if (!inputText.isBlank()) {
            return inputText;
        } else {
            System.out.println("Введите текст");
            return null;
        }
    }
}
