package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Category;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */
public class CategoryDAO extends DAOImpl<Category,Long> {

    @Inject
    public CategoryDAO(Session session) {
        super(session);
    }

    public List<Category> pagination(String search, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    public Integer totalPagination(String search) {
        return null;
    }

    public List<Category> findAllOrderName() {
        return null;
    }
}