package by.gsu.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {

    Optional<T> findById(long id);

    List<T> findAll();

    void save(T t);

    void update(T t);

    void delete(T t);

}
