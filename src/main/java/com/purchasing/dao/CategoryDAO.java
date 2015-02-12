package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Category;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class CategoryDAO extends DAOImpl<Category,Long> {

    @Inject
    public CategoryDAO(Session session) {
        super(session);
    }
}
