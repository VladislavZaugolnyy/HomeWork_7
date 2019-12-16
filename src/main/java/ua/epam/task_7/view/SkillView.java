package ua.epam.task_7.view;

import ua.epam.task_7.controller.SkillController;
import ua.epam.task_7.util.InputReader;

public class SkillView {
    private InputReader inputReader;
    private SkillController skillController = new SkillController();

    private String menu = "Available options (type in a number):\n" +
            "1. Print all skills\n" +
            "2. Show skill by id\n" +
            "3. Add a new skill\n" +
            "4. Update a skill\n" +
            "5. Exit.\n";

    private String skillNameRequest = "Enter the name of a skill";
    private String idRequest = "\nEnter skill ID: ";
    private String incorrectInput = "Incorrect input";

    public SkillView(InputReader reader) {
        this.inputReader = reader;
    }

    public void viewRun() {
        outer:while (true) {
            System.out.println(menu);
            int choice = inputReader.getIntInput();

            switch (choice) {
                case 1: {
                    System.out.println(skillController.getAll());
                    break;
                }
                case 2: {
                    System.out.print(idRequest);
                    int input = inputReader.getIntInput();
                    System.out.println(skillController.getById(input));
                    break;
                }
                case 3: {
                    System.out.println(skillNameRequest);
                    String name = inputReader.getStringInput();
                    System.out.println(skillController.addSkill(name));
                    break;
                }
                case 4: {
                    System.out.println(idRequest);
                    int input = inputReader.getIntInput();
                    System.out.println(skillNameRequest);
                    String name = inputReader.getStringInput();
                    System.out.println(skillController.update(input, name));
                    break;
                }
                case 5: {
                    break outer;
                }
                default: {
                    System.out.println(incorrectInput);
                }
            }
        }
    }
}
