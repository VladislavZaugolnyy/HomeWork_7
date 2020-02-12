package ua.epam.crud.repository;

import ua.epam.crud.exceptions.UniqueException;
import ua.epam.crud.model.BasicEntity;

import java.util.List;

public interface GenericRepository<T extends BasicEntity, ID> {
    T getById(ID id) throws UniqueException;

    List<T> getAll() throws UniqueException;

    void save(T t) throws UniqueException;

    void delete(T t) throws UniqueException;

    void update(T t) throws UniqueException;
}

