package edu.ijse.sms.dao;

import java.util.List;

public class SuperDao<T, ID> implements CrudDao<T, ID> {
    @Override
    public boolean add(T entity) throws Exception {
        // To be implemented by concrete classes
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean update(T entity) throws Exception {
        // To be implemented by concrete classes
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean delete(ID id) throws Exception {
        // To be implemented by concrete classes
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public T find(ID id) throws Exception {
        // To be implemented by concrete classes
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<T> getAll() throws Exception {
        // To be implemented by concrete classes
        throw new UnsupportedOperationException("Method not implemented");
    }
}
