package com.revature.persistence;

public interface Dao<T> {

    // create
    void create(T t);

    // read
    T getByID(int id);

    // update
    boolean update(T t);

    // delete
    boolean deleteByID(int id);
}