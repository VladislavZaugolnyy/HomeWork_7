package ua.epam.task_7.controller;

import ua.epam.task_7.messages.Messages;
import ua.epam.task_7.model.Account;
import ua.epam.task_7.model.AccountStatus;
import ua.epam.task_7.model.Developer;
import ua.epam.task_7.model.Skill;
import ua.epam.task_7.repository.AccountRepository;
import ua.epam.task_7.repository.DeveloperRepository;
import ua.epam.task_7.repository.SkillRepository;
import ua.epam.task_7.repository.io.DeveloperRepositoryImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperController {
    private DeveloperRepository developerRepository;
    private SkillRepository skillRepository;
    private AccountRepository accountRepository;

    public DeveloperController(SkillRepository skillRepository, AccountRepository accountRepository) {
        this.skillRepository = skillRepository;
        this.accountRepository = accountRepository;
        developerRepository = new DeveloperRepositoryImpl(skillRepository, accountRepository);
    }

    public String getAll() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Developer developer : developerRepository.getAll()) {
            stringBuilder.append(developer);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getById(long id) {
        Developer developer = developerRepository.getById(id);
        if (developer == null) {
            return Messages.DEVELOPER_NOT_FOUND.getMessage();
        } else {
            return developer.toString();
        }
    }

    public String addNewDeveloper(String name, String accountData, Set<Long> skillsId) {
        Long id = developerRepository.getLastId();
        Set<Skill> skills = new HashSet<>();
        for (Long skillId : skillsId) {
            Skill skill = skillRepository.getById(skillId);
            if (skill != null) {
                skills.add(skill);
            }
        }
        Long accountId = accountRepository.getLastId();
        Account account = new Account(accountId, accountData, AccountStatus.ACTIVE);
        Developer developer = new Developer(id, name, account, skills);
        List<Developer> developers = developerRepository.getAll();
        if (developers.contains(developer)) {
            return Messages.DEVELOPER_ALREADY_EXISTS.getMessage();
        } else {
            developerRepository.create(developer);
            return Messages.DEVELOPER_ADDED.getMessage();
        }
    }
}
