package ua.epam.task_7;

import ua.epam.task_7.repository.io.AccountRepositoryImpl;
import ua.epam.task_7.repository.io.SkillRepositoryImpl;
import ua.epam.task_7.util.InputReader;
import ua.epam.task_7.view.DeveloperView;
import ua.epam.task_7.view.SkillView;

public class AppRunner {
    public static void main(String[] args) {
        /*SkillView skillView = new SkillView(new InputReader());
        skillView.viewRun();*/
        DeveloperView developerView = new DeveloperView(new InputReader(), new SkillRepositoryImpl(), new AccountRepositoryImpl());
        developerView.viewRun();
    }
}
