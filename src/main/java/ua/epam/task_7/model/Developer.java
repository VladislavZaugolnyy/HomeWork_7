package ua.epam.task_7.model;

import java.util.Collections;
import java.util.Set;

public class Developer extends BaseModel {
    private String name;
    private Account account;
    private Set<Skill> skills;

    public Developer(Long id, String name, Account account, Set<Skill> skills) {
        super(id);
        this.name = name;
        this.account = account;
        this.skills = Collections.unmodifiableSet(skills);
    }

    public Long getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", account=" + account +
                ", skills=" + skills +
                '}';
    }
}
