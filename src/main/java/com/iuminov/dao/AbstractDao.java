package com.iuminov.dao;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDao<T, ID> implements GenericDao<T, ID> {

    protected final Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public T create(T t) throws Exception {
        return null;
    }

    @Override
    public T read(ID id) throws Exception {
        return null;
    }

    @Override
    public T update(T t) throws Exception {
        return null;
    }

    @Override
    public void delete(ID id) throws Exception {

    }

    @Override
    public List<T> readAll() throws Exception {
        return null;
    }
}
