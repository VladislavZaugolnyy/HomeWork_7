package ua.epam.crud.model;

public class BasicEntity {
    private Long id;

    public Long getId() {
        return id;
    }

    public BasicEntity(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}