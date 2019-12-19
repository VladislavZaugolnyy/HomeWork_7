package ua.epam.task_7.view;

import ua.epam.task_7.controller.SkillController;
import ua.epam.task_7.messages.Messages;
import ua.epam.task_7.util.InputReader;

public class SkillView {
    private InputReader inputReader;
    private SkillController controller = new SkillController();

    public SkillView(InputReader reader) {
        this.inputReader = reader;
    }

    public void viewRun() {
        outer:while (true) {
            System.out.println(Messages.SKILL_MENU.getMessage());
            int choice = inputReader.getIntInput();

            switch (choice) {
                case 1: {
                    System.out.println(controller.getAll());
                    break;
                }
                case 2: {
                    System.out.print(Messages.SKILL_ID_REQUEST.getMessage());
                    int input = inputReader.getIntInput();
                    System.out.println(controller.getById(input));
                    break;
                }
                case 3: {
                    System.out.println(Messages.SKILL_REQUEST.getMessage());
                    String name = inputReader.getInput();
                    System.out.println(controller.addSkill(name));
                    break;
                }
                case 4: {
                    System.out.println(Messages.SKILL_ID_REQUEST.getMessage());
                    int input = inputReader.getIntInput();
                    System.out.println(Messages.SKILL_REQUEST.getMessage());
                    String name = inputReader.getInput();
                    System.out.println(controller.update(input, name));
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
}
