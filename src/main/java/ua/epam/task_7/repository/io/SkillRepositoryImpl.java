package ua.epam.task_7.repository.io;

import ua.epam.task_7.exceptions.FileProcessingException;
import ua.epam.task_7.exceptions.DoesNotExistException;
import ua.epam.task_7.model.Skill;
import ua.epam.task_7.repository.SkillRepository;
import ua.epam.task_7.util.FileProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SkillRepositoryImpl implements SkillRepository {
    private FileProcessor fileProcessor = new FileProcessor("src\\main\\resources\\Skill.txt");

    @Override
    public Skill getById(Long id) {
        Skill skill = null;
        try {
            for (String string : fileProcessor.readFile()) {
                if (string.startsWith("id=" + id)) {
                    skill = deserialize(string);
                    break;
                }
            }
        } catch (FileProcessingException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skills = new ArrayList<>();
        try {
            for (String string : fileProcessor.readFile()) {
                Skill skill = deserialize(string);
                if (skill.getId() != null && skill.getName() != null) {
                    skills.add(skill);
                }
            }
        } catch (FileProcessingException e) {
            e.printStackTrace();
        }
        return skills;
    }

    @Override
    public void create(Skill skill) {
        List<Skill> skills = getAll();
        skills.add(skill);
        serialize(skills);
    }

    @Override
    public void delete(Skill skill) {
        List<Skill> skills = getAll();
        if (skills.remove(skill)) {
            serialize(skills);
        }
    }

    @Override
    public void update(Skill skill) throws DoesNotExistException {
        List<Skill> skills = getAll();
        boolean updated = skills.removeIf(skl -> skl.getId().equals(skl.getId()));
        if (!updated) {
            throw new DoesNotExistException();
        }
        skills.add(skill);
        serialize(skills);
    }

    private void serialize(Collection<Skill> collection) {
        List<String> serialized = collection.stream().map(this::skillToString).collect(Collectors.toList());
        try {
            fileProcessor.writeToFile(serialized);
        } catch (FileProcessingException e) {
            e.printStackTrace();
        }
    }

    private Skill deserialize(String string) {
        Long id = null;
        String name = null;

        String[] parts = string.split("/");
        for (String part : parts) {
            if (part.startsWith("id=")) {
                id = Long.parseLong(part.substring(3));
            }
            if (part.startsWith("name=")) {
                name = part.substring(5);
            }
        }
        return new Skill(id, name);
    }

    private String skillToString(Skill skill) {
        return "id=" + skill.getId() + "/name=" + skill.getName();
    }
}

