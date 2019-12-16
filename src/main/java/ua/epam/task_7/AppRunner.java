package ua.epam.task_7;

import ua.epam.task_7.util.InputReader;
import ua.epam.task_7.view.SkillView;

public class AppRunner {
    public static void main(String[] args) {
        SkillView skillView = new SkillView(new InputReader());
        skillView.viewRun();
    }
}
