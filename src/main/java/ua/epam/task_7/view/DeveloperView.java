package ua.epam.task_7.view;

import ua.epam.task_7.controller.DeveloperController;
import ua.epam.task_7.messages.Messages;
import ua.epam.task_7.repository.AccountRepository;
import ua.epam.task_7.repository.SkillRepository;
import ua.epam.task_7.util.InputReader;

import java.util.HashSet;
import java.util.Set;

public class DeveloperView {
    private InputReader inputReader;
    private DeveloperController controller;

    public DeveloperView(InputReader reader, SkillRepository skillRepository, AccountRepository accountRepository) {
        this.inputReader = reader;
        this.controller = new DeveloperController(skillRepository, accountRepository);
    }

    public void viewRun() {
        outer: while (true) {
            System.out.println(Messages.DEVELOPER_MENU.getMessage());
            int choice = inputReader.getIntInput();

            switch (choice) {
                case 1: {
                    System.out.println(controller.getAll());
                    break;
                }
                case 2: {
                    System.out.println(Messages.DEVELOPER_ID_REQUEST.getMessage());
                    int input = inputReader.getIntInput();
                    System.out.println(controller.getById(input));
                    break;
                }
                case 3: {
                    addDeveloper();
                    break;
                }
                case 4: {
                    updateDeveloper();
                    break;
                }
                case 5: {
                    break outer;
                }
                default: {
                    System.out.println(Messages.WRONG_INPUT.getMessage());
                }
            }
        }
    }

    private void addDeveloper() {
        System.out.println(Messages.DEVELOPER_NAME_REQUEST.getMessage());
        String name = inputReader.getInput();
        System.out.println(Messages.DEVELOPER_INFO_REQUEST.getMessage());
        String accountData = inputReader.getInput();
        long skillChoice;
        Set<Long> skillsId = new HashSet<>();
        while ((skillChoice = inputReader.getIntInput()) != 0) {
            skillsId.add(skillChoice);
        }
        System.out.println(controller.addNewDeveloper(name, accountData, skillsId));
    }

    private void updateDeveloper() {
        System.out.println(Messages.DEVELOPER_ID_REQUEST.getMessage());
        int devId = inputReader.getIntInput();
        System.out.println(Messages.DEVELOPER_ACCOUNT_STATUS_UPDATE.getMessage());
        int accStatus = inputReader.getIntInput();
        System.out.println(Messages.DEVELOPER_SKILL_ID_REQUEST.getMessage());
        Set<Long> skillsId = new HashSet<>();
        long skillChoice;
        while ((skillChoice = inputReader.getIntInput()) != 0) {
            skillsId.add(skillChoice);
        }
    }
}
