package Mappers;

import Classes.Pizza.Pizza;

import java.util.Optional;

public interface DataMapper<T> {
    Optional<T> find(int id);
    void insert(T object);
    void update(T object);
    void delete(T object);

}
