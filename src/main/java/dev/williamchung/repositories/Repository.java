package dev.williamchung.repositories;

import java.util.List;

public interface Repository<T, ID> {
    T findById(ID id);
    List<T> findAll();
    ID save(T obj);
    ID update(T obj);
    void delete(T obj);
}
