package ua.epam.task_7.controller;

import ua.epam.task_7.exceptions.RecordDoesNotExistException;
import ua.epam.task_7.model.Skill;
import ua.epam.task_7.repository.repositoryImpl.SkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private SkillRepositoryImpl repo = new SkillRepositoryImpl();
    private final String skillNotFound = "Skill not found";
    private final String alreadyExists = "Already exists";
    private final String creationSuccessful = "Skill added to repository";
    private final String complete = "operation completed";
    private final String notComplete = "operation denied";

    public String getAll() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Skill> skills = repo.getAll();

        for (Skill skill : skills) {
            stringBuilder.append(skill);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getById(long id) {
        Skill skill = repo.getById(id);
        if (skill == null) {
            return skillNotFound;
        } else {
            return skill.toString();
        }
    }

    public String addSkill(String name) {
        Long id = repo.lastId() + 1;
        Skill skill = new Skill(id, name);
        List<Skill> skills = repo.getAll();
        if (skills.contains(skill)) {
            return alreadyExists;
        } else {
            repo.create(skill);
            return creationSuccessful;
        }
    }

    public String update(long id, String name) {
        Skill skill = new Skill(id, name);
        try {
            repo.update(skill);
            return complete;
        } catch (RecordDoesNotExistException e) {
            e.printStackTrace();
            return notComplete;
        }
    }
}
