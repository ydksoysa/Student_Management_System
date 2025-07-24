package edu.ijse.sms.dao;

import java.util.List;

public interface CrudDao<T, ID> {
    boolean add(T entity) throws Exception;
    boolean update(T entity) throws Exception;
    boolean delete(ID id) throws Exception;
    T find(ID id) throws Exception;
    List<T> getAll() throws Exception;
}
