package com.lrd.repository;

import java.util.List;

public interface Dao<T> {
    public T create(T t);
    public T update(Long id, T t);
    public void delete(Long id);
    public T find(Long id);
    public List<T> findAll();
}
