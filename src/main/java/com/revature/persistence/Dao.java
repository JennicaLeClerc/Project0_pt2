package com.revature.persistence;

public interface Dao<T> {

    // create
    void create(T t);

    // read
    T getByID(int id);

    // update
    void update(T t);

    // delete
    void deleteByID(int id);
}