package ua.epam.crud.mapper;

import ua.epam.crud.exceptions.UniqueException;
import ua.epam.crud.model.BasicEntity;

import java.util.List;

public interface Mapper <T extends BasicEntity, R, V> {
    List<T> map(R r) throws UniqueException;
    void map(T t, V v) throws UniqueException;
}
