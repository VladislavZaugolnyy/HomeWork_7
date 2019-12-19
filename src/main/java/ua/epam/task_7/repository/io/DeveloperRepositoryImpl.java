package ua.epam.task_7.repository.io;

import ua.epam.task_7.exceptions.DoesNotExistException;
import ua.epam.task_7.exceptions.FileProcessingException;
import ua.epam.task_7.model.Account;
import ua.epam.task_7.model.Developer;
import ua.epam.task_7.model.Skill;
import ua.epam.task_7.repository.AccountRepository;
import ua.epam.task_7.repository.DeveloperRepository;
import ua.epam.task_7.repository.SkillRepository;
import ua.epam.task_7.util.FileProcessor;

import java.util.*;
import java.util.stream.Collectors;

public class DeveloperRepositoryImpl implements DeveloperRepository {
    private FileProcessor fileProcessor = new FileProcessor("src\\main\\resources\\Developer.txt");
    private AccountRepository accountRepository;
    private SkillRepository skillRepository;

    public DeveloperRepositoryImpl(SkillRepository skillRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public Developer getById(Long id) {
        Developer developer = null;
        try {
            for (String string : fileProcessor.readFile()) {
                if (string.startsWith("id=" + id)) {
                    developer = deserialize(string);
                }
            }
        } catch (FileProcessingException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> list = new ArrayList<>();
        try {
            for (String string : fileProcessor.readFile()) {
                Developer developer = deserialize(string);
                if (developer.getId() != null) {
                    list.add(developer);
                }
            }
        } catch (FileProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void create(Developer developer) {
        List<Developer> developers = getAll();
        developers.add(developer);
        serialize(developers);
    }

    @Override
    public void delete(Developer developer) {
        List<Developer> developers = getAll();
        if (developers.remove(developer)) {
            serialize(developers);
        }
    }

    @Override
    public void update(Developer developer) throws DoesNotExistException {
        List<Developer> developers = getAll();
        boolean updated = developers.removeIf(dev -> dev.getId().equals(developer.getId()));
        if (!updated) {
            throw new DoesNotExistException();
        }
        developers.add(developer);
        serialize(developers);
    }

    private void serialize(Collection<Developer> collection) {
        List<String> serialized = collection.stream().map(this::developerToString).collect(Collectors.toList());
        try {
            fileProcessor.writeToFile(serialized);
        } catch (FileProcessingException e) {
            e.printStackTrace();
        }
    }

    private Developer deserialize(String line) {
        Long id = null;
        String name = null;
        Account account = null;
        Set<Skill> skills = new HashSet<>();

        String[] parts = line.split("/");
        for (String part : parts) {
            if (part.startsWith("id=")) {
                id = Long.parseLong(part.substring(3));
            }
            if (part.startsWith("name=")) {
                name = part.substring(5);
            }
            if (part.startsWith("account=")) {
                account = accountRepository.getById(Long.parseLong(part.substring(8)));
            }
            if (part.startsWith("skills=")) {
                String[] numbers = part.substring(7).split(",");
                for (String number : numbers) {
                    skills.add(skillRepository.getById(Long.parseLong(number)));
                }
            }
        }
        return new Developer(id, name, account, skills);
    }

    private String developerToString(Developer developer) {
        StringBuilder stringBuilder = new StringBuilder();
        String str = "id=" + developer.getId() + "/name=" + developer.getName()
                + "/account=" + developer.getAccount().getId() + "/skills=";
        stringBuilder.append(str);
        for (Skill skill : developer.getSkills()) {
            stringBuilder.append(skill.getId());
            stringBuilder.append(",");
        }
        if (developer.getSkills().size() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
