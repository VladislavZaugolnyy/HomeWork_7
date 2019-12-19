package ua.epam.task_7.model;

import java.util.Objects;

public class Skill extends BaseModel {
    private String name;

    public Long getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public Skill(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Skill skill = (Skill) o;
        return Objects.equals(getName(), skill.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                '}';
    }
}