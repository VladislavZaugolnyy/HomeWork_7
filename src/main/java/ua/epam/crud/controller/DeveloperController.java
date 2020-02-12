package ua.epam.crud.controller;

import ua.epam.crud.messages.Messages;
import ua.epam.crud.model.Account;
import ua.epam.crud.model.Developer;
import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.AccountRepository;
import ua.epam.crud.repository.DeveloperRepository;
import ua.epam.crud.repository.SkillRepository;
import ua.epam.crud.repository.io.DeveloperRepositoryImpl;

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
