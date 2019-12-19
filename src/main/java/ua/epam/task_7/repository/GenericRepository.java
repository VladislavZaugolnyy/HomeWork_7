package ua.epam.task_7.repository;

import ua.epam.task_7.exceptions.DoesNotExistException;
import ua.epam.task_7.model.BaseModel;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T extends BaseModel, ID> {
    T getById(ID id);

    List<T> getAll();

    void create(T t);

    void delete(T t);

    void update(T t) throws DoesNotExistException;

    default Long getLastId() {
        List<T> developers = getAll();
        if (developers.size() == 0) {
            return 0L;
        }
        Optional<Long> optional = developers.stream().map(T::getId).reduce((a, b) -> a.compareTo(b) >= 0 ? a : b);
        return optional.orElse(null);
    }
}
