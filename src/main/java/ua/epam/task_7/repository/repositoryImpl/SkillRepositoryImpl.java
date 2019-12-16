package ua.epam.task_7.repository.repositoryImpl;

import ua.epam.task_7.exceptions.FileProcessingException;
import ua.epam.task_7.exceptions.RecordDoesNotExistException;
import ua.epam.task_7.model.Skill;
import ua.epam.task_7.repository.SkillRepository;
import ua.epam.task_7.util.FileProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SkillRepositoryImpl implements SkillRepository {
    private FileProcessor fileProcessor = new FileProcessor("src/main/resources/Skill.txt");

    @Override
    public Skill getById(Long id) {
        Skill skill = null;
        try {
            for (String line : fileProcessor.readFileLines()) {
                if (line.startsWith("id=" + id)) {
                    skill = deserialize(line);
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
            for (String line : fileProcessor.readFileLines()) {
                Skill skill = deserialize(line);
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

    public Long lastId() {
        List<Skill> skills = getAll();
        if (skills.size() == 0) {
            return 0L;
        }
        Optional<Long> optional = skills.stream().map(Skill::getId).reduce((a, b) -> a.compareTo(b) >= 1 ? a : b);
        return optional.orElse(null);
    }

    @Override
    public void update(Skill updatedSkill) throws RecordDoesNotExistException {
        List<Skill> skills = getAll();
        boolean updated = skills.removeIf(skill -> skill.getId().equals(updatedSkill.getId()));
        if (!updated) {
            throw new RecordDoesNotExistException();
        }
        skills.add(updatedSkill);
        serialize(skills);
    }

    private void serialize(Collection<Skill> collection) {
        List<String> serialized = collection.stream().map(Skill::toString).collect(Collectors.toList());
        try {
            fileProcessor.writeFile(serialized);
        } catch (FileProcessingException e) {
            e.printStackTrace();
        }
    }

    private Skill deserialize(String string) {
        Long id = null;
        String name = null;

        String[] tokens = string.split(" ");
        for (String token : tokens) {
            if (token.startsWith("id=")) {
                id = Long.parseLong(token.substring(3));
            }
            if (token.startsWith("name=")) {
                name = token.substring(5);
            }
        }
        return new Skill(id, name);
    }
}

