package dev.williamchung.repositories;

import java.util.List;

/**
 * This is the Repository interface. It is meant to replicate the JPA CrudRepository from Spring.
 * @param <T>
 *     Generic T is a standin for the object that will be stored into the repo.
 * @param <ID>
 *     Generic ID is a standin for the class that will denote the id of the object in the database (we will use Integer)
 */
public interface Repository<T, ID> {
    T findById(ID id);
    List<T> findAll();
    T save(T obj);
    T update(T obj);
    void delete(T obj);
}
