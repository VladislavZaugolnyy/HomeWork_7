package ua.epam.task_7.repository;

import ua.epam.task_7.exceptions.RecordDoesNotExistException;

import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(ID id);
    List<T> getAll();
    void create(T t);
    void delete(T t);
    void update(T t) throws RecordDoesNotExistException;
}
