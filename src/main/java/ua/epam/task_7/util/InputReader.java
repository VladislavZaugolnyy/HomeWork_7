package ua.epam.task_7.util;

import ua.epam.task_7.messages.Messages;

import java.util.Scanner;

public class InputReader {
    private Scanner scanner = new Scanner(System.in);

    public int getIntInput() {
        int result;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("\n" + Messages.WRONG_INPUT.getMessage());
                scanner.next();
            }
            result = scanner.nextInt();
        } while (result < 1);
        return result;
    }

    public String getInput() {
        scanner.nextLine();
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
