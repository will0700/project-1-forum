package dev.williamchung.repositories;

import java.util.List;

public interface Repository<T, ID> {
    T findById(ID id);
    List<T> findAll();
    T save(T obj);
    T update(T obj);
    void delete(T obj);
}
