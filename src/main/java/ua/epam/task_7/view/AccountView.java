package ua.epam.task_7.view;

import ua.epam.task_7.controller.AccountController;
import ua.epam.task_7.messages.Messages;
import ua.epam.task_7.util.InputReader;

public class AccountView {
    private InputReader inputReader;
    private AccountController controller = new AccountController();

    public AccountView(InputReader reader) {
        this.inputReader = reader;
    }

    public void run() {
        outer:
        while (true) {
            System.out.println(Messages.ACCOUNT_MENU.getMessage());
            int choice = inputReader.getIntInput();

            switch (choice) {
                case 1: {
                    System.out.println(controller.getAll());
                    break;
                }
                case 2: {
                    System.out.println(Messages.ACCOUNT_ID_REQUEST.getMessage());
                    int input = inputReader.getIntInput();
                    System.out.println(controller.getById(input));
                    break;
                }
                case 3: {
                    System.out.println(Messages.ACCOUNT_INFO_REQUEST.getMessage());
                    String data = inputReader.getInput();
                    System.out.println(controller.addNewAccount(data));
                    break;
                }
                case 4: {
                    System.out.println(Messages.ACCOUNT_ID_REQUEST.getMessage());
                    int input = inputReader.getIntInput();
                    System.out.println(Messages.ACCOUNT_INFO_REQUEST.getMessage());
                    String data = inputReader.getInput();
                    System.out.println(controller.update(input, data));
                    break;
                }
                case 5: {
                    System.out.println(Messages.ACCOUNT_ID_REQUEST.getMessage());
                    int input = inputReader.getIntInput();
                    System.out.println(controller.disable(input));
                    break;
                }
                case 6: {
                    break outer;
                }
                default: {
                    System.out.println(Messages.WRONG_INPUT.getMessage());
                }
            }
        }
    }

}
