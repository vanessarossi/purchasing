package com.purchasing.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author vanessa
 */
public interface BaseDAO<T, I extends Serializable> {

    public T save(T entity);

    public void delete(T entity);

    public List<T> findAll(Class<T> clazz);

    public T findById(Class<T> clazz, Long pk);

}
