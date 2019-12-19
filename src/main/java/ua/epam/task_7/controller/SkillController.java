package ua.epam.task_7.controller;

import ua.epam.task_7.exceptions.DoesNotExistException;
import ua.epam.task_7.messages.Messages;
import ua.epam.task_7.model.Skill;
import ua.epam.task_7.repository.SkillRepository;
import ua.epam.task_7.repository.io.SkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private SkillRepository skillRepository = new SkillRepositoryImpl();

    public String getAll() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Skill> skills = skillRepository.getAll();

        for (Skill skill : skills) {
            stringBuilder.append(skill);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getById(long id) {
        Skill skill = skillRepository.getById(id);
        if (skill == null) {
            return Messages.SKILL_NOT_FOUND.getMessage();
        } else {
            return skill.toString();
        }
    }

    public String addSkill(String name) {
        Long id = skillRepository.getLastId() + 1;
        Skill skill = new Skill(id, name);
        List<Skill> skills = skillRepository.getAll();
        if (skills.contains(skill)) {
            return Messages.SKILL_ALREADY_EXISTS.getMessage();
        } else {
            skillRepository.create(skill);
            return Messages.SKILL_ADDED.getMessage();
        }
    }

    public String update(long id, String name) {
        Skill skill = new Skill(id, name);
        try {
            skillRepository.update(skill);
            return Messages.SUCCESS.getMessage();
        } catch (DoesNotExistException e) {
            e.printStackTrace();
            return Messages.FAIL.getMessage();
        }
    }
}
