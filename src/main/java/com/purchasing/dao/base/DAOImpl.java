package com.purchasing.dao.base;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author vanessa
 */
public class DAOImpl<T, I extends Serializable> implements  BaseDAO<T,I> {

    private Session session;

    public DAOImpl(Session session) {
        this.session = session;
    }

    @Override
    @Transactional
    public T save(T entity){
        T save = null;
        try {
            save = (T) session.merge(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return save;
    }

    @Override
    @Transactional
    public void delete(T entity){
        try {
                session.delete(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> findAll(Class<T> clazz) {
        List<T> result = this.session.createCriteria(clazz).list();
        return result;
    }

    @Override
    public T findById(Class<T> clazz, Long pk) {
        return (T) this.session.createCriteria(clazz).add(Restrictions.idEq(pk)).uniqueResult();
    }

    public Session getSession() {
        return this.session;
    }
}
