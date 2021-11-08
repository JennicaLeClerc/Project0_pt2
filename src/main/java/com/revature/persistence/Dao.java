package com.revature.persistence;

import java.util.List;

public interface Dao<T> {

    // create
    void create(T t);

    // read
    T getByID(int id);

    List<T> getAll();

    // update
    boolean update(T t);

    // delete
    boolean deleteByID(int id);
}